package com.example.EcoMarket_SPA.Services;

import java.util.List;
import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Repository.PedidoRepository;
import com.example.EcoMarket_SPA.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PedidoServices {
    @Autowired
    private final ProductoRepository productoRepository;
    private PedidoRepository pedidoRepository;

    public PedidoServices(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Pedido> getPedidos() {
        return pedidoRepository.getPedidos();
    }

    public Pedido getPedido(int id) {
        return pedidoRepository.getPedido(id);
    }

    public String deletePedido(int id) {
        productoRepository.deleteProducto(id);
        return "Pedido eliminado";
    }

    public Pedido updatePedido(Pedido pedido) {
        Producto existingproducto = productoRepository.getProducto(pedido.getProductos().get(0).getId());
        return pedido;
    }
}
