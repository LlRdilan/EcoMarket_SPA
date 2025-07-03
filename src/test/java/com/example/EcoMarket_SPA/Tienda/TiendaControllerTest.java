package com.example.EcoMarket_SPA.Tienda;

import com.example.EcoMarket_SPA.Controller.TiendaController;
import com.example.EcoMarket_SPA.Model.Tienda;
import com.example.EcoMarket_SPA.Services.TiendaServices;
import com.example.EcoMarket_SPA.Assemblers.TiendaModelAssemblers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TiendaController.class)
public class TiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TiendaServices tiendaServices;

    @MockBean
    private TiendaModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllTiendas() throws Exception {
        Tienda tienda = new Tienda(1, "EcoMarket", "Av. Siempre Viva 123", "Santiago");
        List<Tienda> lista = List.of(tienda);

        when(tiendaServices.getTiendas()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(tienda)))
        );

        mockMvc.perform(get("/api/tiendas"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("EcoMarket")));
    }

    @Test
    void testGetTiendaById() throws Exception {
        Tienda tienda = new Tienda(1, "EcoMarket", "Av. Siempre Viva 123", "Santiago");

        when(tiendaServices.getTiendaId(1)).thenReturn(tienda);
        when(assembler.toModel(tienda)).thenReturn(EntityModel.of(tienda));

        mockMvc.perform(get("/api/tiendas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("EcoMarket"));
    }

    @Test
    void testCreateTienda() throws Exception {
        Tienda tienda = new Tienda(1, "EcoMarket", "Av. Siempre Viva 123", "Santiago");

        when(tiendaServices.saveTienda(tienda)).thenReturn(tienda);

        mockMvc.perform(post("/api/tiendas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tienda)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("EcoMarket"));
    }

    @Test
    void testDeleteTienda() throws Exception {
        when(tiendaServices.deleteTienda(1)).thenReturn(true);

        mockMvc.perform(delete("/api/tiendas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Tienda eliminada"));
    }
}
