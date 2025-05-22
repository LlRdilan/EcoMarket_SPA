package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Factura;
import com.example.EcoMarket_SPA.Services.FacturaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaServices facturaServices;

    @GetMapping
    public List<Factura> getFacturas() {
        return facturaServices.getFacturas();
    }

    @GetMapping("/{id}")
    public Factura getFactura(@PathVariable int id) {
        return facturaServices.obtenerFactura(id);
    }

    @PostMapping
    public Factura crearFactura(@RequestBody Factura factura) {
        return facturaServices.guardarFactura(factura);
    }

    @PutMapping("/{id}")
    public Factura actualizarFactura(@PathVariable int id, @RequestBody Factura factura) {
        factura.setId(id);
        return facturaServices.actualizarFactura(factura);
    }

    @DeleteMapping("/{id}")
    public String eliminarFactura(@PathVariable int id) {
        boolean eliminado = facturaServices.eliminarFactura(id);
        return eliminado ? "Factura eliminada" : "Factura no encontrada";
    }
}
