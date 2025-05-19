package com.example.EcoMarket_SPA.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    private int id;
    private Producto producto;
    private Tienda tienda;

}
