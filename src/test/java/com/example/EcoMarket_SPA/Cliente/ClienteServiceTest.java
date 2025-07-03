package com.example.EcoMarket_SPA.Cliente;

import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Repository.ClienteRepository;
import com.example.EcoMarket_SPA.Services.ClienteServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteServices clienteServices;

    Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente(1, "Juan", "Pérez", "juan.perez@email.com", "12345678-9", "123456789", "Santiago");
    }

    @Test
    void testObtenerTodosLosClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> result = clienteServices.getClientes();

        assertFalse(result.isEmpty());
        assertEquals("Juan", result.get(0).getNombre());
    }

    @Test
    void testObtenerClientePorId_Existe() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Cliente result = clienteServices.obtenerCliente(1);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testAgregarCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteServices.guardarCliente(cliente);

        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testEliminarCliente_Existe() {
        when(clienteRepository.existsById(1)).thenReturn(true);
        doNothing().when(clienteRepository).deleteById(1);

        boolean result = clienteServices.eliminarCliente(1);

        assertTrue(result);
    }
}
