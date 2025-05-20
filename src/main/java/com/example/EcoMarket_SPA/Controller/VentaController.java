package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Venta;
import com.example.EcoMarket_SPA.Services.VentaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ventas")
public class VentaController {
    @Autowired
    private VentaServices ventaServices;

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaServices.getVentas();
    }

    @PostMapping
    public Venta agregarVenta(@RequestBody Venta venta) {
        return ventaServices.saveVenta(venta);
    }

    @GetMapping({"{id}"})
    public Venta buscarVenta(@PathVariable int id) {
        return ventaServices.getVentaId(id);
    }

    @PutMapping({"{id}"})
    public Venta actualizarVenta(@PathVariable int id, @RequestBody Venta venta) {
        return ventaServices.updateVenta(venta);
    }

    @DeleteMapping({"{id}"})
    public String eliminarLibro(@PathVariable int id) {
        return ventaServices.deleteVenta(id);
    }
}
