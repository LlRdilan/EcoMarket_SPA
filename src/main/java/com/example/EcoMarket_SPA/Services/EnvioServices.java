package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Envio;
import com.example.EcoMarket_SPA.Repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioServices {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> getEnvios() {
        return envioRepository.obetenrListaEnvios();
    }

    public Envio guardarEnvio(Envio envio) {
        return envioRepository.guardarEnvio(envio);
    }

    public Envio obtenerEnvio(int id) {
        return envioRepository.buscarEnvio(id);
    }

    public Envio actualizarEnvio(Envio envio) {
        return envioRepository.actualizarEnvio(envio);
    }

    public String eliminarEnvio(int id) {
        envioRepository.eliminarEnvio(id);
        return "Envio eliminado";
    }
}
