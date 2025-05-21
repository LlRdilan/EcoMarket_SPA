package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Tienda;
import com.example.EcoMarket_SPA.Repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiendaServices {
    @Autowired
    private TiendaRepository tiendaRepository;

    public List<Tienda> getTiendas(){
        return tiendaRepository.getTiendas();
    }

    public Tienda saveTienda (Tienda tienda) {
        return tiendaRepository.guardar(tienda);
    }

    public Tienda getTiendaId(int id) {
        return tiendaRepository.buscarPorId(id);
    }

    public Tienda updateTienda(Tienda tienda){
        return tiendaRepository.actualizar(tienda);
    }

    public String  deleteTienda(int id) {
        tiendaRepository.eliminar(id);
        return "Tienda Eliminada";
    }
}
