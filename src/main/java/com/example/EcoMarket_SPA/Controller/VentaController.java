package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Venta;
import com.example.EcoMarket_SPA.Services.VentaServices;
import com.example.EcoMarket_SPA.Assemblers.VentaModelAssemblers;
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
@RequestMapping("/api/ventas")
@Tag(name = "API Ventas", description = "Servicios para gestión de ventas")
public class VentaController {

    @Autowired
    private VentaServices ventaServices;

    @Autowired
    private VentaModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar ventas", description = "Retorna todas las ventas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ventas encontradas"),
            @ApiResponse(responseCode = "404", description = "No hay ventas registradas")
    })
    public ResponseEntity<CollectionModel<EntityModel<Venta>>> getVentas() {
        List<Venta> ventas = ventaServices.getVentas();
        if (ventas.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(ventas));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venta por ID", description = "Retorna una venta por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta encontrada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<EntityModel<Venta>> getVenta(
            @Parameter(description = "ID de la venta a buscar") @PathVariable int id) {

        Venta venta = ventaServices.getVentaId(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(venta));
    }

    @PostMapping
    @Operation(summary = "Crear venta", description = "Agrega una nueva venta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta creada exitosamente")
    })
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaServices.saveVenta(venta));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar venta", description = "Actualiza una venta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<Venta> actualizarVenta(
            @Parameter(description = "ID de la venta a actualizar") @PathVariable int id,
            @RequestBody Venta venta) {

        Venta actualizada = ventaServices.updateVenta(id, venta);
        return (actualizada != null)
                ? ResponseEntity.ok(actualizada)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venta", description = "Elimina una venta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta eliminada"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    public ResponseEntity<String> eliminarVenta(
            @Parameter(description = "ID de la venta a eliminar") @PathVariable int id) {

        boolean eliminado = ventaServices.deleteVenta(id);
        return eliminado
                ? ResponseEntity.ok("Venta eliminada")
                : ResponseEntity.status(404).body("Venta no encontrada");
    }
}
