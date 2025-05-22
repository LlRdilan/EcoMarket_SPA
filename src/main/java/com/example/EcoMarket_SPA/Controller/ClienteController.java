package com.example.EcoMarket_SPA.Controller;


import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteServices.getClientes();
    }

    @PostMapping
    public Cliente agregarCliente(@RequestBody Cliente cliente) {
        return clienteServices.guardarCliente(cliente);
    }

    @GetMapping("{id}")
    public Cliente buscarClientePorId(@PathVariable int id) {
        return clienteServices.obtenerCliente(id);
    }

    @PutMapping("{id}")
    public Cliente actualizarCliente(PathVariable id, @RequestBody Cliente cliente) {
        return clienteServices.actualizarCliente(cliente);
    }

    @DeleteMapping("{id}")
    public String eliminarCliente(@PathVariable int id) {
        return clienteServices.eliminarCliente(id);
    }
}
