package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Proovedor;
import com.example.EcoMarket_SPA.Services.ProovedorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proovedores")
public class ProovedorController {

    @Autowired
    private ProovedorServices proovedorServices;

    @GetMapping
    public List<Proovedor> getAllProovedores() {
        return proovedorServices.getProovedores();
    }

    @GetMapping("/{id}")
    public Proovedor getProovedor(@PathVariable int id) {
        return proovedorServices.getProovedor(id);
    }

    @PostMapping
    public Proovedor crearProovedor(@RequestBody Proovedor proovedor) {
        return proovedorServices.saveProovedor(proovedor);
    }

    @PutMapping("/{id}")
    public Proovedor actualizarProovedor(@PathVariable int id, @RequestBody Proovedor proovedor) {
        return proovedorServices.updateProovedor(id, proovedor);
    }

    @DeleteMapping("/{id}")
    public String eliminarProovedor(@PathVariable int id) {
        boolean eliminado = proovedorServices.deleteProovedor(id);
        return eliminado ? "Proovedor eliminado" : "Proovedor no encontrado";
    }
}
