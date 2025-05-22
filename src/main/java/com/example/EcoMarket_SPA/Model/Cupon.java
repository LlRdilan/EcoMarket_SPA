package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cupon {
    private int id;
    private String codigo;
    private double descuento;
    private LocalDate fechaExpiracion;
    private Boolean estado;
}
