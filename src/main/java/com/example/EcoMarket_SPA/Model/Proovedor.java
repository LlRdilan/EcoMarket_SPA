package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proovedor {
    private int id;
    private String nombre;
    private String contacto;
    private String email;
    private List<Producto> productosSuministrados;

}
