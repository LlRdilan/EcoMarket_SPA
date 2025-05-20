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

    public List<Venta> getVentas(){
    return ventaRepository.getVentas();
    }

    public Venta saveVenta (Venta venta) {
        return ventaRepository.guardar(venta);
    }

    public Venta getVentaId(int id) {
        return ventaRepository.buscarPorId(id);
    }

    public Venta updateVenta(Venta venta){
        return ventaRepository.actualizar(venta);
    }

    public String  deleteVenta(int id) {
        ventaRepository.eliminar(id);
        return "Venta Eliminado";
    }

}
