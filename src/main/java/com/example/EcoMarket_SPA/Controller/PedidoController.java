package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoServices pedidoServices;

    @GetMapping
    public List<Pedido> getPedidos() {
        return pedidoServices.getPedidos();
    }

    @GetMapping("/{id}")
    public Pedido getPedido(@PathVariable int id) {
        return pedidoServices.getPedido(id);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoServices.savePedido(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable int id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return pedidoServices.updatePedido(pedido);
    }

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable int id) {
        boolean eliminado = pedidoServices.deletePedido(id);
        return eliminado ? "Pedido eliminado" : "Pedido no encontrado";
    }
}
