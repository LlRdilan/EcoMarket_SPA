package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Inventario;
import com.example.EcoMarket_SPA.Services.InventarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioServices inventarioServices;

    @GetMapping
    public List<Inventario> getInventario() {
        return inventarioServices.getInventario();
    }

    @GetMapping("/{id}")
    public Inventario getInventarioPorId(@PathVariable int id) {
        return inventarioServices.getInventarioId(id);
    }

    @PostMapping
    public Inventario crearInventario(@RequestBody Inventario inventario) {
        return inventarioServices.saveInventario(inventario);
    }

    @PutMapping("/{id}")
    public Inventario actualizarInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        inventario.setId(id);
        return inventarioServices.updateInventario(inventario);
    }

    @DeleteMapping("/{id}")
    public String eliminarInventario(@PathVariable int id) {
        boolean eliminado = inventarioServices.deleteInventario(id);
        return eliminado ? "Inventario eliminado" : "Inventario no encontrado";
    }
}
