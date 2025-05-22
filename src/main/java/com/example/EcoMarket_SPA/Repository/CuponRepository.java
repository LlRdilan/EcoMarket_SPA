package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Cupon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CuponRepository {

    private List<Cupon> listaCupones =  new ArrayList<Cupon>();

    public List<Cupon> obtenerListaCupones() {
        return listaCupones;
    }

    public Cupon buscarCuponPorId(int id) {
        for (Cupon cupon : listaCupones) {
            if (cupon.getId() == id) {
                return cupon;
            }
        }
        return null;
    }

    public Cupon agregarCupon(Cupon cupon) {
        listaCupones.add(cupon);
        return cupon;
    }

    public Cupon actualizarCupon(Cupon cupon) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaCupones.size(); i++) {
            if (listaCupones.get(i).getId() == cupon.getId()) {
                id = cupon.getId();
                idPosicion = i;

            }
        }

        Cupon cupon1 = new Cupon();
        cupon1.setId(id);
        cupon1.setCodigo(cupon.getCodigo());
        cupon1.setDescuento(cupon.getDescuento());
        cupon1.setEstado(cupon.getEstado());
        cupon1.setFechaExpiracion(cupon.getFechaExpiracion());

        listaCupones.set(idPosicion, cupon1);
        return cupon1;
    }

    public void eliminarCupon(int id) {
        Cupon cupon = buscarCuponPorId(id);
        if (cupon != null) {
            listaCupones.remove(cupon);
        }
    }

}
