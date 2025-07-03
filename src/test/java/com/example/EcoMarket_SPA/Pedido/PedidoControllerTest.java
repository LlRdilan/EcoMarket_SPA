package com.example.EcoMarket_SPA.Pedido;

import com.example.EcoMarket_SPA.Controller.PedidoController;
import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Services.PedidoServices;
import com.example.EcoMarket_SPA.Assemblers.PedidoModelAssemblers;
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

@WebMvcTest(controllers = PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoServices pedidoServices;

    @MockBean
    private PedidoModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllPedidos() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setTotal(10000);
        pedido.setEstadoPedido("Pendiente");

        List<Pedido> lista = List.of(pedido);

        when(pedidoServices.getPedidos()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(pedido)))
        );

        mockMvc.perform(get("/api/pedidos"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Pendiente")));
    }

    @Test
    void testGetPedidoById() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setTotal(10000);
        pedido.setEstadoPedido("Pendiente");

        when(pedidoServices.getPedido(1)).thenReturn(pedido);
        when(assembler.toModel(pedido)).thenReturn(EntityModel.of(pedido));

        mockMvc.perform(get("/api/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estadoPedido").value("Pendiente"));
    }

    @Test
    void testCreatePedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setTotal(10000);
        pedido.setEstadoPedido("Pendiente");

        when(pedidoServices.savePedido(pedido)).thenReturn(pedido);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estadoPedido").value("Pendiente"));
    }

    @Test
    void testDeletePedido() throws Exception {
        when(pedidoServices.deletePedido(1)).thenReturn(true);

        mockMvc.perform(delete("/api/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedido eliminado"));
    }
}
