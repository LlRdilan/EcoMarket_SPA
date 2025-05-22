package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Pedido;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.EcoMarket_SPA.Repository.ProductoRepository;
import com.example.EcoMarket_SPA.Services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {
        @Autowired
        private PedidoServices pedidoServices;

        @GetMapping
        public ResponseEntity<List<Pedido>> getAllPedidos() {
            List<Pedido> pedidos = pedidoServices.getPedidos();
            return new ResponseEntity<>(pedidos, HttpStatus.OK);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
            Pedido pedido = pedidoServices.getPedido(id);
            if (pedido != null) {
                return new ResponseEntity<>(pedido, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PostMapping
        public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido) {
            Pedido nuevoPedido = pedidoServices.savePedido(pedido);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Pedido> updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
            pedido.setId(id);
            Pedido actualizado = pedidoServices.updatePedido(pedido);
            if (actualizado != null) {
                return new ResponseEntity<>(actualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletePedido(@PathVariable int id) {
            pedidoServices.deletePedido(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
