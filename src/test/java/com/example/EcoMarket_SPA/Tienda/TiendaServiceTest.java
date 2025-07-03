package com.example.EcoMarket_SPA.Tienda;

import com.example.EcoMarket_SPA.Model.Tienda;
import com.example.EcoMarket_SPA.Repository.TiendaRepository;
import com.example.EcoMarket_SPA.Services.TiendaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TiendaServiceTest {

    @Mock
    TiendaRepository tiendaRepository;

    @InjectMocks
    TiendaServices tiendaServices;

    Tienda tienda;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tienda = new Tienda(1, "EcoMarket", "Av. Siempre Viva 123", "Santiago");
    }

    @Test
    void testObtenerTodasLasTiendas() {
        when(tiendaRepository.findAll()).thenReturn(List.of(tienda));

        List<Tienda> result = tiendaServices.getTiendas();

        assertFalse(result.isEmpty());
        assertEquals("EcoMarket", result.get(0).getNombre());
    }

    @Test
    void testObtenerTiendaPorId() {
        when(tiendaRepository.findById(1)).thenReturn(Optional.of(tienda));

        Tienda result = tiendaServices.getTiendaId(1);

        assertNotNull(result);
        assertEquals("EcoMarket", result.getNombre());
    }

    @Test
    void testGuardarTienda() {
        when(tiendaRepository.save(tienda)).thenReturn(tienda);

        Tienda result = tiendaServices.saveTienda(tienda);

        assertEquals("EcoMarket", result.getNombre());
    }

    @Test
    void testEliminarTienda() {
        when(tiendaRepository.existsById(1)).thenReturn(true);
        doNothing().when(tiendaRepository).deleteById(1);

        boolean result = tiendaServices.deleteTienda(1);

        assertTrue(result);
    }
}
