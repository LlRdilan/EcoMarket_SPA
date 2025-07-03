package com.example.EcoMarket_SPA.Inventario;

import com.example.EcoMarket_SPA.Assemblers.InventarioModelAssemblers;
import com.example.EcoMarket_SPA.Controller.InventarioController;
import com.example.EcoMarket_SPA.Model.Inventario;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Model.Tienda;
import com.example.EcoMarket_SPA.Services.InventarioServices;
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

@WebMvcTest(InventarioController.class)
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioServices inventarioServices;

    @MockBean
    private InventarioModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllInventarios() throws Exception {
        Producto producto = new Producto(1, "Cepillo", 1000, "desc", 10);
        Tienda tienda = new Tienda(1, "Tienda A", "Dirección", "Ciudad");
        Inventario inventario = new Inventario(1, producto, tienda);

        List<Inventario> lista = List.of(inventario);

        when(inventarioServices.getInventario()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(inventario)))
        );

        mockMvc.perform(get("/api/inventarios"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetInventarioById() throws Exception {
        Producto producto = new Producto(1, "Cepillo", 1000, "desc", 10);
        Tienda tienda = new Tienda(1, "Tienda A", "Dirección", "Ciudad");
        Inventario inventario = new Inventario(1, producto, tienda);

        when(inventarioServices.getInventarioId(1)).thenReturn(inventario);
        when(assembler.toModel(inventario)).thenReturn(EntityModel.of(inventario));

        mockMvc.perform(get("/api/inventarios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateInventario() throws Exception {
        Producto producto = new Producto(1, "Cepillo", 1000, "desc", 10);
        Tienda tienda = new Tienda(1, "Tienda A", "Dirección", "Ciudad");
        Inventario inventario = new Inventario(1, producto, tienda);

        when(inventarioServices.saveInventario(inventario)).thenReturn(inventario);

        mockMvc.perform(post("/api/inventarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventario)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteInventario() throws Exception {
        when(inventarioServices.deleteInventario(1)).thenReturn(true);

        mockMvc.perform(delete("/api/inventarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Inventario eliminado"));
    }
}
