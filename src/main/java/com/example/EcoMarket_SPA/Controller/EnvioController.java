package com.example.EcoMarket_SPA.Controller;


import com.example.EcoMarket_SPA.Model.Envio;
import com.example.EcoMarket_SPA.Services.EnvioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{/api/v1/envio}")
public class EnvioController {

    @Autowired
    private EnvioServices envioServices;

    @GetMapping
    public List<Envio> listarEnvios() {
        return envioServices.getEnvios();
    }

    @PostMapping
    public Envio agregarEnvio(@RequestBody Envio envio) {
        return envioServices.guardarEnvio(envio);
    }

    @GetMapping("{id}")
    public Envio buscarEnvioPorId(@PathVariable int id) {
        return envioServices.obtenerEnvio(id);
    }

    @PutMapping("{id}")
    public Envio actualizarEnvio(@PathVariable int id,@RequestBody Envio envio) {
        return envioServices.actualizarEnvio(envio);
    }

    @DeleteMapping("{id}")
    public String eliminarEnvio(@PathVariable int id) {
        return envioServices.eliminarEnvio(id);
    }
}
