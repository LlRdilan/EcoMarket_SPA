package com.example.EcoMarket_SPA.Producto;

import com.example.EcoMarket_SPA.Controller.ProductoController;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Services.ProductoServices;
import com.example.EcoMarket_SPA.Assemblers.ProductoModelAssemblers;
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

@WebMvcTest(controllers = ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //No entiendo por que el MockBean me sale asi subrayado en rojo
    @MockBean
    private ProductoServices productoServices;

    @MockBean
    private ProductoModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllProducts() throws Exception {
        Producto producto = new Producto(1, "Cepillo de Bambú", 1990, "Cepillo biodegradable ecológico", 100);
        List<Producto> lista = List.of(producto);

        when(productoServices.getAllProductos()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(producto)))
        );

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Cepillo de Bambú")));
    }

    @Test
    void testGetProductById() throws Exception {
        Producto producto = new Producto(1, "Cepillo de Bambú", 1990, "Cepillo biodegradable ecológico", 100);

        when(productoServices.getProducto(1)).thenReturn(producto);
        when(assembler.toModel(producto)).thenReturn(EntityModel.of(producto));

        mockMvc.perform(get("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cepillo de Bambú"));
    }

    @Test
    void testCreateProduct() throws Exception {
        Producto producto = new Producto(1, "Cepillo de Bambú", 1990, "Cepillo biodegradable ecológico", 100);

        when(productoServices.saveProducto(producto)).thenReturn(producto);

        mockMvc.perform(post("/api/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cepillo de Bambú"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        when(productoServices.deleteProducto(1)).thenReturn(true);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado"));
    }
}
