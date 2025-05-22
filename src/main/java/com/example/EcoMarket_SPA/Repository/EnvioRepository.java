package com.example.EcoMarket_SPA.Repository;


import com.example.EcoMarket_SPA.Model.Envio;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnvioRepository {

    private List<Envio> listaEnvios = new ArrayList<Envio>();

    public List<Envio> obetenrListaEnvios() {
        return listaEnvios;
    }

    public Envio buscarEnvio(int id) {
        for (Envio envio : listaEnvios) {
            if (envio.getId() == id) {
                return envio;
            }
        }
        return null;
    }

    public Envio guardarEnvio(Envio envio) {
        listaEnvios.add(envio);
        return envio;
    }


    public Envio actualizarEnvio(Envio envio) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaEnvios.size(); i++) {
            if (listaEnvios.get(i).getId() == envio.getId()) {
                id = envio.getId();
                idPosicion = i;
            }
        }

        Envio envio1 = new Envio();
        envio1.setId(id);
        envio1.setCliente(envio.getCliente());
        envio1.setPedido(envio.getPedido());
        envio1.setEstadoEnvio(envio.getEstadoEnvio());

        listaEnvios.add(idPosicion, envio1);
        return envio1;
    }


    public void eliminarEnvio(int id) {
        Envio envio = buscarEnvio(id);
        if (envio != null) {
            listaEnvios.remove(envio);
        }
    }

}
