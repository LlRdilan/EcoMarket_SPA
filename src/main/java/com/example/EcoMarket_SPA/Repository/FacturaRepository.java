package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Factura;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FacturaRepository {

    private List<Factura> listaFacturas = new ArrayList<Factura>();

    public List<Factura> obtenerFacturas() {
        return listaFacturas;
    }

    public Factura buscarFactura(int id) {
        for (Factura factura : listaFacturas) {
            if (factura.getId() == id) {
                return factura;
            }
        }
        return null;
    }

    public Factura agregarFactura(Factura factura) {
        listaFacturas.add(factura);
        return factura;
    }

    public Factura actualizarFactura(Factura factura) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaFacturas.size(); i++) {
            if (listaFacturas.get(i).getId() == factura.getId()) {
                id =factura.getId();
                idPosicion = i;
            }
        }

        Factura factura1 = new Factura();
        factura1.setId(id);
        factura1.setCliente(factura.getCliente());
        factura1.setVenta(factura.getVenta());
        factura1.setTotal(factura.getTotal());
        factura1.setFechaEmision(factura.getFechaEmision());

        listaFacturas.set(idPosicion, factura1);
        return factura1;
    }

    public void eliminarFactura(int id) {
        Factura factura = buscarFactura(id);
        if (factura != null) {
            listaFacturas.remove(factura);
        }
    }

}
