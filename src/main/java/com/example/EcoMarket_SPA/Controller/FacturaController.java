package com.example.EcoMarket_SPA.Controller;


import com.example.EcoMarket_SPA.Model.Factura;
import com.example.EcoMarket_SPA.Services.FacturaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{/api/v1/factura}")
public class FacturaController {

    @Autowired
    private FacturaServices facturaServices;

    @GetMapping
    public List<Factura> listarFacturas() {
        return facturaServices.getFacturas();
    }

    @PostMapping
    public Factura agregarFactura(@RequestBody Factura factura) {
        return facturaServices.guardarFactura(factura);
    }

    @GetMapping("{id}")
    public Factura buscarFacturaPorId(@PathVariable int id) {
        return facturaServices.obtenerFactura(id);
    }

    @PutMapping("{id}")
    public Factura actualizarFactura(@PathVariable int id,@RequestBody Factura factura) {
        return facturaServices.actualizarFactura(factura);
    }

    @DeleteMapping("{id}")
    public String eliminarFactura(@PathVariable int id) {
        return facturaServices.eliminarFactura(id);
    }
}
