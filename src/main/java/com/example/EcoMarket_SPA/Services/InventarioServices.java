package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Inventario;
import com.example.EcoMarket_SPA.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioServices {

    @Autowired
    private InventarioRepository inventarioRepository;

    public List<Inventario> getInventario() {
        return inventarioRepository.findAll();
    }

    public Inventario getInventarioId(int id) {
        return inventarioRepository.findById(id).orElse(null);
    }

    public Inventario saveInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public Inventario updateInventario(Inventario inventario) {
        if (inventarioRepository.existsById(inventario.getId())) {
            return inventarioRepository.save(inventario);
        }
        return null;
    }

    public boolean deleteInventario(int id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
