package com.example.EcoMarket_SPA.Cliente;

import com.example.EcoMarket_SPA.Controller.ClienteController;
import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Services.ClienteServices;
import com.example.EcoMarket_SPA.Assemblers.ClienteModelAssemblers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

@WebMvcTest(controllers = ClienteController.class)
@AutoConfigureMockMvc //Pobre con pero no me resulto nada

public class ClienteControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteServices clienteServices;

    @MockBean
    private ClienteModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllClientes() throws Exception {
        Cliente cliente = new Cliente(1, "Juan", "Pérez", "juan.perez@email.com", "12345678-9", "123456789", "Santiago");
        List<Cliente> lista = List.of(cliente);

        when(clienteServices.getClientes()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(cliente)))
        );

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Juan")));
    }

    @Test
    void testGetClienteById() throws Exception {
        Cliente cliente = new Cliente(1, "Juan", "Pérez", "juan.perez@email.com", "12345678-9", "123456789", "Santiago");

        when(clienteServices.obtenerCliente(1)).thenReturn(cliente);
        when(assembler.toModel(cliente)).thenReturn(EntityModel.of(cliente));

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void testCreateCliente() throws Exception {
        Cliente cliente = new Cliente(1, "Juan", "Pérez", "juan.perez@email.com", "12345678-9", "123456789", "Santiago");

        when(clienteServices.guardarCliente(cliente)).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void testDeleteCliente() throws Exception {
        when(clienteServices.eliminarCliente(1)).thenReturn(true);

        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente eliminado"));
    }
}
