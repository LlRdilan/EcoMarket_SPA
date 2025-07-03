package com.example.EcoMarket_SPA.Usuario;

import com.example.EcoMarket_SPA.Controller.UsuarioController;
import com.example.EcoMarket_SPA.Model.Usuario;
import com.example.EcoMarket_SPA.Services.UsuarioServices;
import com.example.EcoMarket_SPA.Assemblers.UsuarioModelAssemblers;
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

@WebMvcTest(controllers = UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioServices usuarioServices;

    @MockBean
    private UsuarioModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsuarios() throws Exception {
        Usuario usuario = new Usuario(1, "usuario@mail.com", "Juan", "admin", "secreta123");
        List<Usuario> lista = List.of(usuario);

        when(usuarioServices.getUsuarios()).thenReturn(lista);
        when(assembler.toCollectionModel(lista)).thenReturn(
                CollectionModel.of(List.of(EntityModel.of(usuario)))
        );

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("usuario@mail.com")));
    }

    @Test
    void testGetUsuarioById() throws Exception {
        Usuario usuario = new Usuario(1, "usuario@mail.com", "Juan", "admin", "secreta123");

        when(usuarioServices.getUsuarioId(1)).thenReturn(usuario);
        when(assembler.toModel(usuario)).thenReturn(EntityModel.of(usuario));

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("usuario@mail.com"));
    }

    @Test
    void testCreateUsuario() throws Exception {
        Usuario usuario = new Usuario(1, "usuario@mail.com", "Juan", "admin", "secreta123");

        when(usuarioServices.saveUsuario(usuario)).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("usuario@mail.com"));
    }

    @Test
    void testDeleteUsuario() throws Exception {
        when(usuarioServices.deleteUsuario(1)).thenReturn(true);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario eliminado"));
    }
}
