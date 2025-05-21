package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Tienda;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TiendaRepository {
    private ArrayList<Tienda> tiendas = new ArrayList<>();

    //Metodo que retornara todas las ventas
    public List<Tienda> getTiendas() {
        return tiendas;
    }

    //Metodo que mostrara el libro segun su id
    public Tienda buscarPorId(int id) {
        for (Tienda tienda : tiendas) {
            if (tienda.getId() == id) {
                return tienda;
            }
        }
        return null;
    }

    //Metodo que guardara una venta
    public Tienda guardar(Tienda tienda) {
        tiendas.add(tienda);
        return tienda;
    }

    //Metodo que actualizara la venta
    public Tienda actualizar(Tienda tienda) {
        int id = 0;
        int idPosition = 0;

        for(int i = 0; i < tiendas.size(); i++) {
            if(tiendas.get(i).getId() == tienda.getId()) {
                id = tienda.getId();
                idPosition = i;
            }
        }
        Tienda tienda1 = new Tienda();
        tienda1.setId(id);
        tienda1.setNombre(tienda.getNombre());
        tienda1.setDireccion(tienda.getDireccion());
        tienda1.setCiudad(tienda.getCiudad());

        tiendas.set(idPosition, tienda);
        return tienda1;
    }

    //Metodo que eliminara por id
    public void eliminar(int id){
        Tienda tienda = buscarPorId(id);
        if (tienda != null){
            tiendas.remove(tienda);
        }
    }
}
