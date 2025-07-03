package com.example.EcoMarket_SPA.Proovedor;

import com.example.EcoMarket_SPA.Model.Proovedor;
import com.example.EcoMarket_SPA.Repository.ProovedorRepository;
import com.example.EcoMarket_SPA.Services.ProovedorServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProovedorServiceTest {

    @Mock
    ProovedorRepository proovedorRepository;

    @InjectMocks
    ProovedorServices proovedorServices;

    Proovedor proovedor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        proovedor = new Proovedor(1, "VerdeSur", "987654321", "contacto@verdesur.cl", null);
    }

    @Test
    void testGetAllProovedores() {
        when(proovedorRepository.findAll()).thenReturn(List.of(proovedor));

        List<Proovedor> result = proovedorServices.getProovedores();

        assertFalse(result.isEmpty());
        assertEquals("VerdeSur", result.get(0).getNombre());
    }

    @Test
    void testGetProovedorById() {
        when(proovedorRepository.findById(1)).thenReturn(Optional.of(proovedor));

        Proovedor result = proovedorServices.getProovedor(1);

        assertNotNull(result);
        assertEquals("VerdeSur", result.getNombre());
    }

    @Test
    void testSaveProovedor() {
        when(proovedorRepository.save(proovedor)).thenReturn(proovedor);

        Proovedor result = proovedorServices.saveProovedor(proovedor);

        assertEquals("VerdeSur", result.getNombre());
    }

    @Test
    void testDeleteProovedor() {
        when(proovedorRepository.existsById(1)).thenReturn(true);
        doNothing().when(proovedorRepository).deleteById(1);

        boolean result = proovedorServices.deleteProovedor(1);

        assertTrue(result);
    }
}
