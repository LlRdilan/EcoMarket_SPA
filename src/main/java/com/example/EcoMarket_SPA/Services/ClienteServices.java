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
        return clienteRepository.findAll();
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obtenerCliente(int id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente actualizarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getId())) {
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public boolean eliminarCliente(int id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
