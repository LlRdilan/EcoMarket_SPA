package com.example.EcoMarket_SPA.Envio;

import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Model.Envio;
import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Repository.EnvioRepository;
import com.example.EcoMarket_SPA.Services.EnvioServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnvioServiceTest {

    @Mock
    EnvioRepository envioRepository;

    @InjectMocks
    EnvioServices envioServices;

    Envio envio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        envio = new Envio(1, new Pedido(), new Cliente(), true);
    }

    @Test
    void testObtenerTodosLosEnvios() {
        when(envioRepository.findAll()).thenReturn(List.of(envio));

        List<Envio> result = envioServices.getEnvios();

        assertFalse(result.isEmpty());
    }

    @Test
    void testObtenerEnvioPorId_Existe() {
        when(envioRepository.findById(1)).thenReturn(Optional.of(envio));

        Envio result = envioServices.obtenerEnvio(1);

        assertNotNull(result);
    }

    @Test
    void testAgregarEnvio() {
        when(envioRepository.save(envio)).thenReturn(envio);

        Envio result = envioServices.guardarEnvio(envio);

        assertNotNull(result);
    }

    @Test
    void testEliminarEnvio_Existe() {
        when(envioRepository.existsById(1)).thenReturn(true);
        doNothing().when(envioRepository).deleteById(1);

        boolean result = envioServices.eliminarEnvio(1);

        assertTrue(result);
    }
}
