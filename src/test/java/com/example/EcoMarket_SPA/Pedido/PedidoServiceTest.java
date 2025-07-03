package com.example.EcoMarket_SPA.Pedido;

import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Repository.PedidoRepository;
import com.example.EcoMarket_SPA.Services.PedidoServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {

    @Mock
    PedidoRepository pedidoRepository;

    @InjectMocks
    PedidoServices pedidoServices;

    Pedido pedido;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedido = new Pedido();
        pedido.setId(1);
        pedido.setTotal(10000);
        pedido.setEstadoPedido("Pendiente");
    }

    @Test
    void testObtenerTodosLosPedidos() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> result = pedidoServices.getPedidos();

        assertFalse(result.isEmpty());
        assertEquals("Pendiente", result.get(0).getEstadoPedido());
    }

    @Test
    void testObtenerPedidoPorId() {
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));

        Pedido result = pedidoServices.getPedido(1);

        assertNotNull(result);
        assertEquals(10000, result.getTotal());
    }

    @Test
    void testGuardarPedido() {
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido result = pedidoServices.savePedido(pedido);

        assertEquals("Pendiente", result.getEstadoPedido());
    }

    @Test
    void testEliminarPedido() {
        when(pedidoRepository.existsById(1)).thenReturn(true);
        doNothing().when(pedidoRepository).deleteById(1);

        boolean result = pedidoServices.deletePedido(1);

        assertTrue(result);
    }
}
