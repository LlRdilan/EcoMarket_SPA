package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Venta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VentaRepository {
    private ArrayList<Venta> ventas = new ArrayList<>();

    //Metodo que retornara todas las ventas
    public List<Venta> getVentas() {
        return ventas;
    }

    //Metodo que mostrara el libro segun su id
    public Venta buscarPorId(int id) {
        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                return venta;
            }
        }
        return null;
    }

    //Metodo que guardara una venta
    public Venta guardar(Venta venta) {
        ventas.add(venta);
        return venta;
    }

    //Metodo que actualizara la venta
    public Venta actualizar(Venta venta) {
        int id = 0;
        int idPosition = 0;

        for(int i = 0; i < ventas.size(); i++) {
            if(ventas.get(i).getId() == venta.getId()) {
                id = venta.getId();
                idPosition = i;
            }
        }
        Venta venta1 = new Venta();
        venta1.setId(id);
        venta1.setFechaVenta(venta.getFechaVenta());
        venta1.setDescuento(venta.isDescuento()); //Cuando es un boolean no es get sino is.
        venta1.setTotal(venta.getTotal());

        ventas.set(idPosition, venta1);
        return venta1;
    }

    //Metodo que eliminara por id
    public void eliminar(int id){
        Venta venta = buscarPorId(id);
        if (venta != null){
            ventas.remove(venta);
        }
    }

}
