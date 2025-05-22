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

    public List<Tienda> getTiendas() {
        return tiendaRepository.findAll();
    }

    public Tienda saveTienda(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    public Tienda getTiendaId(int id) {
        return tiendaRepository.findById(id).orElse(null);
    }

    public Tienda updateTienda(int id, Tienda tienda) {
        if (tiendaRepository.existsById(id)) {
            tienda.setId(id);
            return tiendaRepository.save(tienda);
        }
        return null;
    }

    public boolean deleteTienda(int id) {
        if (tiendaRepository.existsById(id)) {
            tiendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
