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
        return envioRepository.findAll();
    }

    public Envio guardarEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public Envio obtenerEnvio(int id) {
        return envioRepository.findById(id).orElse(null);
    }

    public Envio actualizarEnvio(Envio envio) {
        if (envioRepository.existsById(envio.getId())) {
            return envioRepository.save(envio);
        }
        return null;
    }

    public boolean eliminarEnvio(int id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
