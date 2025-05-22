package com.example.EcoMarket_SPA.Services;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.EcoMarket_SPA.Model.Inventario;
import com.example.EcoMarket_SPA.Repository.InventarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InventarioServices {
    private InventarioRepository inventarioRepository;

    public List<Inventario> getInventario(Inventario i) {
        return inventarioRepository.getInventario();
    }

    public Inventario getInventario(int id) {
        return inventarioRepository.getInventario(id);
    }

    public String deleteInventario(int id) {
        inventarioRepository.deleteInventario(id);
        return "Inventario eliminado";
    }
    public Inventario updateInventario(Inventario inventario) {
        Inventario old = inventarioRepository.getInventario(inventario.getId());
        return old;
    }

}
