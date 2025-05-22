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

    public List<Factura> getFacturas(){
        return facturaRepository.obtenerFacturas();
    }

    public Factura guardarFactura(Factura factura){
        return facturaRepository.agregarFactura(factura);
    }

    public Factura obtenerFactura(int id){
        return facturaRepository.buscarFactura(id);
    }

    public Factura actualizarFactura(Factura factura){
        return facturaRepository.actualizarFactura(factura);
    }

    public String eliminarFactura(int id){
        facturaRepository.eliminarFactura(id);
        return "Factura eliminada";
    }
}
