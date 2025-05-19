package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private Cliente cliente;
    private Producto producto;
    private int calificacion;
    private String descripcion;
    private LocalDate fecha;
}
