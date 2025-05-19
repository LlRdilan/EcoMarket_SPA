package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {
    private int id;
    private String nombre;
    private String direccion;
    private String ciudad;

}
