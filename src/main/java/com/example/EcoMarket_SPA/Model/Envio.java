package com.example.EcoMarket_SPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    private int id;
    private Pedido pedido;
    private Cliente cliente;
    private Boolean estadoEnvio;

}
