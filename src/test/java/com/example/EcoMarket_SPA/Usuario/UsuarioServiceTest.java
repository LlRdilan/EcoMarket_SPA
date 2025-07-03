package com.example.EcoMarket_SPA.Usuario;

import com.example.EcoMarket_SPA.Model.Usuario;
import com.example.EcoMarket_SPA.Repository.UsuarioRepository;
import com.example.EcoMarket_SPA.Services.UsuarioServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    UsuarioRepository usuarioRepository;

    @InjectMocks
    UsuarioServices usuarioServices;

    Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(1, "usuario@mail.com", "Juan", "admin", "secreta123");
    }

    @Test
    void testObtenerTodosLosUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        List<Usuario> result = usuarioServices.getUsuarios();
        assertFalse(result.isEmpty());
        assertEquals("Juan", result.get(0).getNombre());
    }

    @Test
    void testObtenerUsuarioPorId() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        Usuario result = usuarioServices.getUsuarioId(1);
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testGuardarUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        Usuario result = usuarioServices.saveUsuario(usuario);
        assertEquals("usuario@mail.com", result.getEmail());
    }

    @Test
    void testEliminarUsuario() {
        when(usuarioRepository.existsById(1)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1);
        boolean result = usuarioServices.deleteUsuario(1);
        assertTrue(result);
    }
}
