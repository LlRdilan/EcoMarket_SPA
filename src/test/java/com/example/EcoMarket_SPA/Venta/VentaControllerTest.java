package com.example.EcoMarket_SPA.Venta;

import com.example.EcoMarket_SPA.Controller.VentaController;
import com.example.EcoMarket_SPA.Model.Venta;
import com.example.EcoMarket_SPA.Services.VentaServices;
import com.example.EcoMarket_SPA.Assemblers.VentaModelAssemblers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaServices ventaServices;

    @MockBean
    private VentaModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllVentas() throws Exception {
        Venta venta = new Venta(1, LocalDate.now(), List.of(), 29990, true);
        List<Venta> lista = List.of(venta);

        when(ventaServices.getVentas()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(CollectionModel.of(List.of(EntityModel.of(venta))));

        mockMvc.perform(get("/api/ventas"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("29990")));
    }

    @Test
    void testGetVentaById() throws Exception {
        Venta venta = new Venta(1, LocalDate.now(), List.of(), 29990, true);

        when(ventaServices.getVentaId(1)).thenReturn(venta);
        when(assembler.toModel(venta)).thenReturn(EntityModel.of(venta));

        mockMvc.perform(get("/api/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(29990));
    }

    @Test
    void testCreateVenta() throws Exception {
        Venta venta = new Venta(1, LocalDate.now(), List.of(), 29990, true);

        when(ventaServices.saveVenta(venta)).thenReturn(venta);

        mockMvc.perform(post("/api/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(venta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(29990));
    }

    @Test
    void testDeleteVenta() throws Exception {
        when(ventaServices.deleteVenta(1)).thenReturn(true);

        mockMvc.perform(delete("/api/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta eliminada"));
    }
}
