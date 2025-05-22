package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServices {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedido(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido savePedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Pedido pedido) {
        if (pedidoRepository.existsById(pedido.getId())) {
            return pedidoRepository.save(pedido);
        }
        return null;
    }

    public boolean deletePedido(int id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
