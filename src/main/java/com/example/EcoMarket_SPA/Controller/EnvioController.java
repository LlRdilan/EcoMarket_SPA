package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Envio;
import com.example.EcoMarket_SPA.Services.EnvioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioServices envioServices;

    @GetMapping
    public List<Envio> getEnvios() {
        return envioServices.getEnvios();
    }

    @GetMapping("/{id}")
    public Envio getEnvio(@PathVariable int id) {
        return envioServices.obtenerEnvio(id);
    }

    @PostMapping
    public Envio crearEnvio(@RequestBody Envio envio) {
        return envioServices.guardarEnvio(envio);
    }

    @PutMapping("/{id}")
    public Envio actualizarEnvio(@PathVariable int id, @RequestBody Envio envio) {
        envio.setId(id);
        return envioServices.actualizarEnvio(envio);
    }

    @DeleteMapping("/{id}")
    public String eliminarEnvio(@PathVariable int id) {
        boolean eliminado = envioServices.eliminarEnvio(id);
        return eliminado ? "Envío eliminado" : "Envío no encontrado";
    }
}
