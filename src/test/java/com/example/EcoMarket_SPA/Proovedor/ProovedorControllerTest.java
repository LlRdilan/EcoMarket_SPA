package com.example.EcoMarket_SPA.Proovedor;

import com.example.EcoMarket_SPA.Controller.ProovedorController;
import com.example.EcoMarket_SPA.Model.Proovedor;
import com.example.EcoMarket_SPA.Services.ProovedorServices;
import com.example.EcoMarket_SPA.Assemblers.ProovedorModelAssemblers;
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

@WebMvcTest(controllers = ProovedorController.class)
public class ProovedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProovedorServices proovedorServices;

    @MockBean
    private ProovedorModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProovedores() throws Exception {
        Proovedor proovedor = new Proovedor(1, "VerdeSur", "987654321", "contacto@verdesur.cl", null);
        List<Proovedor> lista = List.of(proovedor);

        when(proovedorServices.getProovedores()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(proovedor)))
        );

        mockMvc.perform(get("/api/proovedores"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("VerdeSur")));
    }

    @Test
    void testGetProovedorById() throws Exception {
        Proovedor proovedor = new Proovedor(1, "VerdeSur", "987654321", "contacto@verdesur.cl", null);

        when(proovedorServices.getProovedor(1)).thenReturn(proovedor);
        when(assembler.toModel(proovedor)).thenReturn(EntityModel.of(proovedor));

        mockMvc.perform(get("/api/proovedores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("VerdeSur"));
    }

    @Test
    void testCreateProovedor() throws Exception {
        Proovedor proovedor = new Proovedor(1, "VerdeSur", "987654321", "contacto@verdesur.cl", null);

        when(proovedorServices.saveProovedor(proovedor)).thenReturn(proovedor);

        mockMvc.perform(post("/api/proovedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proovedor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("VerdeSur"));
    }

    @Test
    void testDeleteProovedor() throws Exception {
        when(proovedorServices.deleteProovedor(1)).thenReturn(true);

        mockMvc.perform(delete("/api/proovedores/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Proveedor eliminado"));
    }
}
