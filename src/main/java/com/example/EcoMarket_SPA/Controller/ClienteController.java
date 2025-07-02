package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Assemblers.ClienteModelAssemblers;
import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Services.ClienteServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "API Clientes", description = "Servicios para gestión de clientes")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @Autowired
    private ClienteModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar clientes", description = "Retorna todos los clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay clientes registrados")
    })
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> getClientes() {
        // Verificar si la lista está vacía
        List<Cliente> clientes = clienteServices.getClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(clientes));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<EntityModel<Cliente>> getCliente(
            @Parameter(description = "ID del cliente a buscar") @PathVariable int id) {

        // Verificar si el cliente existe
        Cliente cliente = clienteServices.obtenerCliente(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(cliente));
    }

    @PostMapping
    @Operation(summary = "Agregar cliente", description = "Agrega un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente agregado exitosamente")
    })
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteServices.guardarCliente(cliente));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Actualiza un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> actualizarCliente(
            @Parameter(description = "ID del cliente a actualizar") @PathVariable int id,
            @RequestBody Cliente cliente) {

        cliente.setId(id);
        Cliente actualizado = clienteServices.actualizarCliente(cliente);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<String> eliminarCliente(
            @Parameter(description = "ID del cliente a eliminar") @PathVariable int id) {

        // Verificar si el cliente fue eliminado
        boolean eliminado = clienteServices.eliminarCliente(id);
        return eliminado
                ? ResponseEntity.ok("Cliente eliminado")
                : ResponseEntity.status(404).body("Cliente no encontrado");
    }
}
