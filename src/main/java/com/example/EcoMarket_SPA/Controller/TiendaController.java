package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Tienda;
import com.example.EcoMarket_SPA.Services.TiendaServices;
import com.example.EcoMarket_SPA.Assemblers.TiendaModelAssemblers;
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
@RequestMapping("/api/tiendas")
@Tag(name = "API Tiendas", description = "Servicios para gestión de tiendas")
public class TiendaController {

    @Autowired
    private TiendaServices tiendaServices;

    @Autowired
    private TiendaModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar tiendas", description = "Retorna todas las tiendas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tiendas encontradas"),
            @ApiResponse(responseCode = "404", description = "No hay tiendas registradas")
    })
    public ResponseEntity<CollectionModel<EntityModel<Tienda>>> getTiendas() {
        List<Tienda> tiendas = tiendaServices.getTiendas();
        if (tiendas.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(tiendas));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener tienda por ID", description = "Retorna una tienda por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tienda encontrada"),
            @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    public ResponseEntity<EntityModel<Tienda>> getTienda(
            @Parameter(description = "ID de la tienda a buscar") @PathVariable int id) {

        Tienda tienda = tiendaServices.getTiendaId(id);
        if (tienda == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(tienda));
    }

    @PostMapping
    @Operation(summary = "Crear tienda", description = "Agrega una nueva tienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tienda creada exitosamente")
    })
    public ResponseEntity<Tienda> crearTienda(@RequestBody Tienda tienda) {
        return ResponseEntity.ok(tiendaServices.saveTienda(tienda));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tienda", description = "Actualiza una tienda existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tienda actualizada"),
            @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    public ResponseEntity<Tienda> actualizarTienda(
            @Parameter(description = "ID de la tienda a actualizar") @PathVariable int id,
            @RequestBody Tienda tienda) {

        Tienda actualizada = tiendaServices.updateTienda(id, tienda);
        return (actualizada != null)
                ? ResponseEntity.ok(actualizada)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tienda", description = "Elimina una tienda por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tienda eliminada"),
            @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    public ResponseEntity<String> eliminarTienda(
            @Parameter(description = "ID de la tienda a eliminar") @PathVariable int id) {

        boolean eliminado = tiendaServices.deleteTienda(id);
        return eliminado
                ? ResponseEntity.ok("Tienda eliminada")
                : ResponseEntity.status(404).body("Tienda no encontrada");
    }
}
