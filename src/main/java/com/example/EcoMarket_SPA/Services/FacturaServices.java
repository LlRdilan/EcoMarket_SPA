package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Factura;
import com.example.EcoMarket_SPA.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServices {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> getFacturas() {
        return facturaRepository.findAll();
    }

    public Factura guardarFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura obtenerFactura(int id) {
        return facturaRepository.findById(id).orElse(null);
    }

    public Factura actualizarFactura(Factura factura) {
        if (facturaRepository.existsById(factura.getId())) {
            return facturaRepository.save(factura);
        }
        return null;
    }

    public boolean eliminarFactura(int id) {
        if (facturaRepository.existsById(id)) {
            facturaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
