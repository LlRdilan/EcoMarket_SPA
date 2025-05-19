package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private int id;
    private String email;
    private String nombre;
    private String rol;
    private String password;

}
