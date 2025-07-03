package com.example.EcoMarket_SPA.Venta;

import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Model.Venta;
import com.example.EcoMarket_SPA.Repository.VentaRepository;
import com.example.EcoMarket_SPA.Services.VentaServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VentaServiceTest {

    @Mock
    VentaRepository ventaRepository;

    @InjectMocks
    VentaServices ventaServices;

    Venta venta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        venta = new Venta(1, LocalDate.now(), List.of(), 29990, true);
    }

    @Test
    void testObtenerTodasLasVentas() {
        when(ventaRepository.findAll()).thenReturn(List.of(venta));

        List<Venta> result = ventaServices.getVentas();

        assertFalse(result.isEmpty());
        assertEquals(29990, result.get(0).getTotal());
    }

    @Test
    void testObtenerVentaPorId() {
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));

        Venta result = ventaServices.getVentaId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGuardarVenta() {
        when(ventaRepository.save(venta)).thenReturn(venta);

        Venta result = ventaServices.saveVenta(venta);

        assertEquals(29990, result.getTotal());
    }

    @Test
    void testEliminarVenta() {
        when(ventaRepository.existsById(1)).thenReturn(true);
        doNothing().when(ventaRepository).deleteById(1);

        boolean eliminado = ventaServices.deleteVenta(1);

        assertTrue(eliminado);
    }
}
