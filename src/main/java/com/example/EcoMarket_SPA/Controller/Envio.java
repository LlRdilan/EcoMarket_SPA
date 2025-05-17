package com.example.EcoMarket_SPA.Controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {

    private int idEnvio;
    private String direccion;
    private Boolean estadoEnvio;

}
