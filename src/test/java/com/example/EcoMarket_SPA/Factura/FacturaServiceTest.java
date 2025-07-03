package com.example.EcoMarket_SPA.Factura;

import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Model.Factura;
import com.example.EcoMarket_SPA.Model.Venta;
import com.example.EcoMarket_SPA.Repository.FacturaRepository;
import com.example.EcoMarket_SPA.Services.FacturaServices;
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

class FacturaServiceTest {

    @Mock
    FacturaRepository facturaRepository;

    @InjectMocks
    FacturaServices facturaServices;

    Factura factura;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Venta venta = new Venta(); // puedes completar si necesitas usar datos específicos
        Cliente cliente = new Cliente(); // lo mismo
        factura = new Factura(1, LocalDate.of(2024, 7, 1), 19990.0, venta, cliente);
    }

    @Test
    void testObtenerTodasLasFacturas() {
        when(facturaRepository.findAll()).thenReturn(List.of(factura));

        List<Factura> result = facturaServices.getFacturas();

        assertFalse(result.isEmpty());
        assertEquals(19990.0, result.get(0).getTotal());
    }

    @Test
    void testObtenerFacturaPorId_Existe() {
        when(facturaRepository.findById(1)).thenReturn(Optional.of(factura));

        Factura result = facturaServices.obtenerFactura(1);

        assertNotNull(result);
        assertEquals(19990.0, result.getTotal());
    }

    @Test
    void testAgregarFactura() {
        when(facturaRepository.save(factura)).thenReturn(factura);

        Factura result = facturaServices.guardarFactura(factura);

        assertEquals(19990.0, result.getTotal());
    }

    @Test
    void testEliminarFactura_Existe() {
        when(facturaRepository.existsById(1)).thenReturn(true);
        doNothing().when(facturaRepository).deleteById(1);

        boolean result = facturaServices.eliminarFactura(1);

        assertTrue(result);
    }
}
