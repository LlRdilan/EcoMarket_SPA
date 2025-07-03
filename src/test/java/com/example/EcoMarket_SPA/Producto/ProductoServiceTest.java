package com.example.EcoMarket_SPA.Producto;

import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Repository.ProductoRepository;
import com.example.EcoMarket_SPA.Services.ProductoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    ProductoRepository productoRepository;

    @InjectMocks
    ProductoServices productoServices;

    Producto producto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        producto = new Producto(1, "Cepillo de Bambú", 1990, "Cepillo biodegradable ecológico", 100);
    }

    @Test
    void testObtenerTodosLosProductos() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<Producto> result = productoServices.getAllProductos();

        assertFalse(result.isEmpty());
        assertEquals("Cepillo de Bambú", result.get(0).getNombre());
    }

    @Test
    void testObtenerProductoPorId_Existe() {
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        Producto result = productoServices.getProducto(1);

        assertNotNull(result);
        assertEquals("Cepillo de Bambú", result.getNombre());
    }

    @Test
    void testAgregarProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto result = productoServices.saveProducto(producto);

        assertEquals("Cepillo de Bambú", result.getNombre());
    }

    @Test
    void testEliminarProducto_Existe() {
        when(productoRepository.existsById(1)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(1);

        boolean result = productoServices.deleteProducto(1);

        assertTrue(result);
    }
}
