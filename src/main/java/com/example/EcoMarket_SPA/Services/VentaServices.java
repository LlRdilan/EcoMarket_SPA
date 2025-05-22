package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Venta;
import com.example.EcoMarket_SPA.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VentaServices {
    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta getVentaId(int id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta updateVenta(int id, Venta venta) {
        if (ventaRepository.existsById(id)) {
            venta.setId(id);
            return ventaRepository.save(venta);
        }
        return null;
    }

    public boolean deleteVenta(int id) {
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
