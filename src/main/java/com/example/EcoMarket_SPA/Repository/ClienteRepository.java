package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    private List<Cliente> listaClientes = new ArrayList<>();

    public List<Cliente> obtenerListaClientes() {
        return listaClientes;
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        return cliente;
    }

    public Cliente actualizarCliente(Cliente cliente) {
        int id = 0;
        int idPosicion = 0;

    for (int i = 0; i < listaClientes.size(); i++) {
        if (listaClientes.get(i).getId() == cliente.getId()) {
            id = cliente.getId();
            idPosicion = 1;

        }
    }

    Cliente cliente1 = new Cliente();
    cliente1.setId(id);
    cliente1.setNombre(cliente.getNombre());
    cliente1.setApellido(cliente.getApellido());
    cliente1.setTelefono(cliente.getTelefono());
    cliente1.setEmail(cliente.getEmail());

    listaClientes.set(idPosicion, cliente1);
        return cliente1;
    }

    public void eliminarCliente(int id) {
    Cliente cliente = buscarClientePorId(id);
        if (cliente != null) {
            listaClientes.remove(cliente);
    }

    }
}
