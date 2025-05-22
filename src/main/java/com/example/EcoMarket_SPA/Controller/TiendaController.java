package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Tienda;
import com.example.EcoMarket_SPA.Services.TiendaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiendas")
public class TiendaController {
    @Autowired
    private TiendaServices tiendaServices;

    @GetMapping
    public List<Tienda> getTiendas() {
        return tiendaServices.getTiendas();
    }

    @GetMapping("/{id}")
    public Tienda getTienda(@PathVariable int id) {
        return tiendaServices.getTiendaId(id);
    }

    @PostMapping
    public Tienda crearTienda(@RequestBody Tienda tienda) {
        return tiendaServices.saveTienda(tienda);
    }

    @PutMapping("/{id}")
    public Tienda actualizarTienda(@PathVariable int id, @RequestBody Tienda tienda) {
        return tiendaServices.updateTienda(id, tienda);
    }

    @DeleteMapping("/{id}")
    public String eliminarTienda(@PathVariable int id) {
        boolean eliminado = tiendaServices.deleteTienda(id);
        return eliminado ? "Tienda eliminada" : "Tienda no encontrada";
    }
}
