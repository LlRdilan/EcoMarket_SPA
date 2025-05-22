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
        return cuponRepository.obtenerListaCupones();
    }

    public Cupon guardarCupon(Cupon Cupon) {
        return cuponRepository.agregarCupon(Cupon);
    }

    public Cupon obtenerCupon(int id) {
        return cuponRepository.buscarCuponPorId(id);
    }

    public String eliminarCupon(int id) {
        cuponRepository.eliminarCupon(id);
        return "Cupon eliminado";
    }
}
