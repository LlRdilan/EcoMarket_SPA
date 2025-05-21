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
    public List<Tienda> listarTiendas() {
        return tiendaServices.getTiendas();
    }

    @PostMapping
    public Tienda agregarTienda(@RequestBody Tienda tienda) {
        return tiendaServices.saveTienda(tienda);
    }

    @GetMapping({"{id}"})
    public Tienda buscarTienda(@PathVariable int id) {
        return tiendaServices.getTiendaId(id);
    }

    @PutMapping({"{id}"})
    public Tienda actualizarTienda(@PathVariable int id, @RequestBody Tienda tienda) {
        return tiendaServices.updateTienda(tienda);
    }

    @DeleteMapping({"{id}"})
    public String eliminarTienda(@PathVariable int id) {
        return tiendaServices.deleteTienda(id);
    }
}
