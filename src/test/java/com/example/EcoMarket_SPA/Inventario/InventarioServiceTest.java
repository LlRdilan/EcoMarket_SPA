package com.example.EcoMarket_SPA.Inventario;

import com.example.EcoMarket_SPA.Model.Inventario;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Model.Tienda;
import com.example.EcoMarket_SPA.Repository.InventarioRepository;
import com.example.EcoMarket_SPA.Services.InventarioServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioServiceTest {

    @Mock
    InventarioRepository inventarioRepository;

    @InjectMocks
    InventarioServices inventarioServices;

    Inventario inventario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Producto producto = new Producto(1, "Producto de prueba", 1000, "Descripción", 10);
        Tienda tienda = new Tienda(1, "Tienda de prueba", "Dirección de prueba", "Región de prueba");
        inventario = new Inventario(1, producto, tienda);
    }

    @Test
    void testObtenerTodoInventario() {
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));

        List<Inventario> result = inventarioServices.getInventario();

        assertFalse(result.isEmpty());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void testObtenerInventarioPorId() {
        when(inventarioRepository.findById(1)).thenReturn(Optional.of(inventario));

        Inventario result = inventarioServices.getInventarioId(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGuardarInventario() {
        when(inventarioRepository.save(inventario)).thenReturn(inventario);

        Inventario result = inventarioServices.saveInventario(inventario);

        assertEquals(1, result.getId());
    }

    @Test
    void testEliminarInventario() {
        when(inventarioRepository.existsById(1)).thenReturn(true);
        doNothing().when(inventarioRepository).deleteById(1);

        boolean result = inventarioServices.deleteInventario(1);

        assertTrue(result);
    }
}
