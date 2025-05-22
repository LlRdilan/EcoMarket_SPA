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
    public List<Venta> getVentas() {
        return ventaServices.getVentas();
    }

    @GetMapping("/{id}")
    public Venta getVenta(@PathVariable int id) {
        return ventaServices.getVentaId(id);
    }

    @PostMapping
    public Venta crearVenta(@RequestBody Venta venta) {
        return ventaServices.saveVenta(venta);
    }

    @PutMapping("/{id}")
    public Venta actualizarVenta(@PathVariable int id, @RequestBody Venta venta) {
        return ventaServices.updateVenta(id, venta);
    }

    @DeleteMapping("/{id}")
    public String eliminarVenta(@PathVariable int id) {
        boolean eliminado = ventaServices.deleteVenta(id);
        return eliminado ? "Venta eliminada" : "Venta no encontrada";
    }
}
