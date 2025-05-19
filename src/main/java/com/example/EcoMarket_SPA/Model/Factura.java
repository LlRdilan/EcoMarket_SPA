package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    private int id;
    private LocalDate fechaEmision;
    private double total;
    private Venta venta;
    private Cliente cliente;

}
