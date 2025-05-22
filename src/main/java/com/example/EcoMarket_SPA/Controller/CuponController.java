package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Cupon;
import com.example.EcoMarket_SPA.Services.CuponServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupones")
public class CuponController {

    @Autowired
    private CuponServices cuponServices;

    @GetMapping
    public List<Cupon> getCupons() {
        return cuponServices.getCupons();
    }

    @GetMapping("/{id}")
    public Cupon getCupon(@PathVariable int id) {
        return cuponServices.obtenerCupon(id);
    }

    @PostMapping
    public Cupon crearCupon(@RequestBody Cupon cupon) {
        return cuponServices.guardarCupon(cupon);
    }

    @PutMapping("/{id}")
    public Cupon actualizarCupon(@PathVariable int id, @RequestBody Cupon cupon) {
        cupon.setId(id);
        return cuponServices.actualizarCupon(cupon);
    }

    @DeleteMapping("/{id}")
    public String eliminarCupon(@PathVariable int id) {
        boolean eliminado = cuponServices.eliminarCupon(id);
        return eliminado ? "Cupón eliminado" : "Cupón no encontrado";
    }
}
