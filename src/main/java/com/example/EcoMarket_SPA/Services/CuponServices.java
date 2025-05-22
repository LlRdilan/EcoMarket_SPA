package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Cupon;
import com.example.EcoMarket_SPA.Repository.CuponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuponServices {

    @Autowired
    private CuponRepository cuponRepository;

    public List<Cupon> getCupons() {
        return cuponRepository.findAll();
    }

    public Cupon guardarCupon(Cupon cupon) {
        return cuponRepository.save(cupon);
    }

    public Cupon obtenerCupon(int id) {
        return cuponRepository.findById(id).orElse(null);
    }

    public Cupon actualizarCupon(Cupon cupon) {
        if (cuponRepository.existsById(cupon.getId())) {
            return cuponRepository.save(cupon);
        }
        return null;
    }

    public boolean eliminarCupon(int id) {
        if (cuponRepository.existsById(id)) {
            cuponRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
