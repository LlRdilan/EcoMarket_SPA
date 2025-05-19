package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    private int id;
    private LocalDate fechaVenta;
    private List<Producto> productosVendidos;
    private double total;
    private boolean descuento;

}
