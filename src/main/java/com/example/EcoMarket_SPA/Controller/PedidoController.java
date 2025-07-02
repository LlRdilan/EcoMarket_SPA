package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Pedido;
import com.example.EcoMarket_SPA.Services.PedidoServices;
import com.example.EcoMarket_SPA.Assemblers.PedidoModelAssemblers;
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
@RequestMapping("/api/pedidos")
@Tag(name = "API Pedidos", description = "Servicios para gestión de pedidos")
public class PedidoController {

    @Autowired
    private PedidoServices pedidoServices;

    @Autowired
    private PedidoModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Retorna todos los pedidos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay pedidos registrados")
    })
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> getPedidos() {
        // Verificar si la lista está vacía
        List<Pedido> pedidos = pedidoServices.getPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(pedidos));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por ID", description = "Retorna un pedido por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<EntityModel<Pedido>> getPedido(
            @Parameter(description = "ID del pedido a buscar") @PathVariable int id) {

        Pedido pedido = pedidoServices.getPedido(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(pedido));
    }

    @PostMapping
    @Operation(summary = "Crear pedido", description = "Agrega un nuevo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido creado exitosamente")
    })
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoServices.savePedido(pedido));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedido", description = "Actualiza un pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<Pedido> actualizarPedido(
            @Parameter(description = "ID del pedido a actualizar") @PathVariable int id,
            @RequestBody Pedido pedido) {

        pedido.setId(id);
        Pedido actualizado = pedidoServices.updatePedido(pedido);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido", description = "Elimina un pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido eliminado"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    public ResponseEntity<String> eliminarPedido(
            @Parameter(description = "ID del pedido a eliminar") @PathVariable int id) {

        boolean eliminado = pedidoServices.deletePedido(id);
        return eliminado
                ? ResponseEntity.ok("Pedido eliminado")
                : ResponseEntity.status(404).body("Pedido no encontrado");
    }
}
