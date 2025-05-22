package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    // Obtener todos los clientes
    @GetMapping
    public List<Cliente> getClientes() {
        return clienteServices.getClientes();
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable int id) {
        return clienteServices.obtenerCliente(id);
    }

    // Crear un nuevo cliente
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteServices.guardarCliente(cliente);
    }

    // Actualizar un cliente
    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable int id, @RequestBody Cliente cliente) {
        cliente.setId(id); // Asegura que se actualice el cliente correcto
        return clienteServices.actualizarCliente(cliente);
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable int id) {
        boolean eliminado = clienteServices.eliminarCliente(id);
        return eliminado ? "Cliente eliminado" : "Cliente no encontrado";
    }
}
