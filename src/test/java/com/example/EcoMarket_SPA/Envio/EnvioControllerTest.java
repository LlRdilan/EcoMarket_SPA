package com.example.EcoMarket_SPA.Envio;

import com.example.EcoMarket_SPA.Controller.EnvioController;
import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Model.Envio;
import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Services.EnvioServices;
import com.example.EcoMarket_SPA.Assemblers.EnvioModelAssemblers;
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

@WebMvcTest(controllers = EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioServices envioServices;

    @MockBean
    private EnvioModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllEnvios() throws Exception {
        Envio envio = new Envio(1, new Pedido(), new Cliente(), true);
        List<Envio> lista = List.of(envio);

        when(envioServices.getEnvios()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(CollectionModel.of(List.of(EntityModel.of(envio))));

        mockMvc.perform(get("/api/envios"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetEnvioById() throws Exception {
        Envio envio = new Envio(1, new Pedido(), new Cliente(), true);

        when(envioServices.obtenerEnvio(1)).thenReturn(envio);
        when(assembler.toModel(envio)).thenReturn(EntityModel.of(envio));

        mockMvc.perform(get("/api/envios/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateEnvio() throws Exception {
        Envio envio = new Envio(1, new Pedido(), new Cliente(), true);

        when(envioServices.guardarEnvio(envio)).thenReturn(envio);

        mockMvc.perform(post("/api/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(envio)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEnvio() throws Exception {
        when(envioServices.eliminarEnvio(1)).thenReturn(true);

        mockMvc.perform(delete("/api/envios/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Envío eliminado"));
    }
}
