package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Inventario;
import com.example.EcoMarket_SPA.Services.InventarioServices;
import com.example.EcoMarket_SPA.Assemblers.InventarioModelAssemblers;
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
@RequestMapping("/api/inventarios")
@Tag(name = "API Inventarios", description = "Servicios para gestión de inventario")
public class InventarioController {

    @Autowired
    private InventarioServices inventarioServices;

    @Autowired
    private InventarioModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar inventarios", description = "Retorna todos los inventarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventarios encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay inventarios registrados")
    })
    public ResponseEntity<CollectionModel<EntityModel<Inventario>>> getInventario() {
        // Verificar si la lista está vacía
        List<Inventario> inventarios = inventarioServices.getInventario();
        if (inventarios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(inventarios));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inventario por ID", description = "Retorna un inventario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<EntityModel<Inventario>> getInventarioPorId(
            @Parameter(description = "ID del inventario a buscar") @PathVariable int id) {

        Inventario inventario = inventarioServices.getInventarioId(id);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(inventario));
    }

    @PostMapping
    @Operation(summary = "Crear inventario", description = "Agrega un nuevo inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario creado exitosamente")
    })
    public ResponseEntity<Inventario> crearInventario(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioServices.saveInventario(inventario));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar inventario", description = "Actualiza un inventario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<Inventario> actualizarInventario(
            @Parameter(description = "ID del inventario a actualizar") @PathVariable int id,
            @RequestBody Inventario inventario) {

        inventario.setId(id);
        Inventario actualizado = inventarioServices.updateInventario(inventario);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inventario", description = "Elimina un inventario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario eliminado"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    public ResponseEntity<String> eliminarInventario(
            @Parameter(description = "ID del inventario a eliminar") @PathVariable int id) {

        boolean eliminado = inventarioServices.deleteInventario(id);
        return eliminado
                ? ResponseEntity.ok("Inventario eliminado")
                : ResponseEntity.status(404).body("Inventario no encontrado");
    }
}
