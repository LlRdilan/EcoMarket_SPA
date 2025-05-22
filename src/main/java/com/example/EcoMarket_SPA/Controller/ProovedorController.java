package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Proovedor;
import com.example.EcoMarket_SPA.Services.ProovedorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(("/api/proovedores"))
public class ProovedorController {
    @Autowired
    private ProovedorServices proovedorServices;

    @GetMapping
    public List<Proovedor> getAllProovedores() {
        return proovedorServices.getProovedor();
    }

    @GetMapping("/{id}")
    public Proovedor getProovedorById(@PathVariable int id) {
        return proovedorServices.getProovedor(id);
    }

    @PostMapping
    public Proovedor addProovedor(@RequestBody Proovedor proovedor) {
        return proovedorServices.saveProovedor(proovedor);
    }

    @PutMapping("/{id}")
    public Proovedor updateProovedor(@PathVariable int id, @RequestBody Proovedor proovedor) {
        proovedor.setId(id);
        return proovedorServices.updateProovedor(proovedor);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProovedor(@PathVariable int id) {
        return proovedorServices.deleteProovedor(id);
    }
}