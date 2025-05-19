package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int id;
    private Cliente cliente;
    private List<Producto> productos;
    private double total;
    private String estadoPedido;

}
