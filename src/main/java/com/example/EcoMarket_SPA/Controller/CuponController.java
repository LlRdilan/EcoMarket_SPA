package com.example.EcoMarket_SPA.Controller;


import com.example.EcoMarket_SPA.Model.Cupon;
import com.example.EcoMarket_SPA.Repository.CuponRepository;
import com.example.EcoMarket_SPA.Services.CuponServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{/api/v1/cupon}")
public class CuponController {

    @Autowired
    private CuponServices cuponServices;

    @GetMapping
    public List<Cupon> listarCupones() {
        return cuponServices.getCupons();
    }

    @PostMapping
    public Cupon agregarCupon(Cupon cupon) {
        return cuponServices.guardarCupon(cupon);
    }

    @GetMapping("{id}")
    public Cupon buscarCuponPorId(@PathVariable int id) {
        return cuponServices.obtenerCupon(id);
    }

    @PutMapping("{id}")
    public Cupon actualizarCupon(@PathVariable int id, @RequestBody Cupon cupon) {
        return cuponServices.actualizarCupon(cupon);
    }

    @DeleteMapping("{id}")
    public String eliminarCupon(@PathVariable int id) {
        return cuponServices.eliminarCupon(id);
    }
}
