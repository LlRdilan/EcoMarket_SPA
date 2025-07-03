package com.example.EcoMarket_SPA.Cupon;

import com.example.EcoMarket_SPA.Model.Cupon;
import com.example.EcoMarket_SPA.Repository.CuponRepository;
import com.example.EcoMarket_SPA.Services.CuponServices;
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

class CuponServiceTest {

    @Mock
    CuponRepository cuponRepository;

    @InjectMocks
    CuponServices cuponServices;

    Cupon cupon;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cupon = new Cupon(1, "DESC10", 10.0, LocalDate.now().plusDays(5), true);
    }

    @Test
    void testObtenerTodosLosCupones() {
        when(cuponRepository.findAll()).thenReturn(List.of(cupon));

        List<Cupon> result = cuponServices.getCupons();

        assertFalse(result.isEmpty());
        assertEquals("DESC10", result.get(0).getCodigo());
    }

    @Test
    void testObtenerCuponPorId_Existe() {
        when(cuponRepository.findById(1)).thenReturn(Optional.of(cupon));

        Cupon result = cuponServices.obtenerCupon(1);

        assertNotNull(result);
        assertEquals("DESC10", result.getCodigo());
    }

    @Test
    void testAgregarCupon() {
        when(cuponRepository.save(cupon)).thenReturn(cupon);

        Cupon result = cuponServices.guardarCupon(cupon);

        assertEquals("DESC10", result.getCodigo());
    }

    @Test
    void testEliminarCupon_Existe() {
        when(cuponRepository.existsById(1)).thenReturn(true);
        doNothing().when(cuponRepository).deleteById(1);

        boolean result = cuponServices.eliminarCupon(1);

        assertTrue(result);
    }
}
