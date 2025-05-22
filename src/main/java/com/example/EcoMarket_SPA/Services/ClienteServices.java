package com.example.EcoMarket_SPA.Services;


import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        return clienteRepository.obtenerListaClientes();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.agregarCliente(cliente);
    }

    public Cliente obtenerCliente(int id) {
        return clienteRepository.buscarClientePorId(id);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.actualizarCliente(cliente);
    }

    public String eliminarCliente(int id) {
        clienteRepository.eliminarCliente(id);
        return "Cliente eliminado";
    }
}
