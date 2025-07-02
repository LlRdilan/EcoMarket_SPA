package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Factura;
import com.example.EcoMarket_SPA.Services.FacturaServices;
import com.example.EcoMarket_SPA.Assemblers.FacturaModelAssemblers;
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
@RequestMapping("/api/facturas")
@Tag(name = "API Facturas", description = "Servicios para gestión de facturas")
public class FacturaController {

    @Autowired
    private FacturaServices facturaServices;

    @Autowired
    private FacturaModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar facturas", description = "Retorna todas las facturas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Facturas encontradas"),
            @ApiResponse(responseCode = "404", description = "No hay facturas registradas")
    })
    public ResponseEntity<CollectionModel<EntityModel<Factura>>> getFacturas() {
        // Verificar si la lista está vacía
        List<Factura> facturas = facturaServices.getFacturas();
        if (facturas.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(facturas));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener factura por ID", description = "Retorna una factura por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura encontrada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<EntityModel<Factura>> getFactura(
            @Parameter(description = "ID de la factura a buscar") @PathVariable int id) {

        Factura factura = facturaServices.obtenerFactura(id);
        if (factura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(factura));
    }

    @PostMapping
    @Operation(summary = "Agregar factura", description = "Agrega una nueva factura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura agregada exitosamente")
    })
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaServices.guardarFactura(factura));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar factura", description = "Actualiza una factura existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura actualizada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Factura> actualizarFactura(
            @Parameter(description = "ID de la factura a actualizar") @PathVariable int id,
            @RequestBody Factura factura) {

        factura.setId(id);
        Factura actualizada = facturaServices.actualizarFactura(factura);
        return (actualizada != null)
                ? ResponseEntity.ok(actualizada)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar factura", description = "Elimina una factura por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Factura eliminada"),
            @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<String> eliminarFactura(
            @Parameter(description = "ID de la factura a eliminar") @PathVariable int id) {

        boolean eliminado = facturaServices.eliminarFactura(id);
        return eliminado
                ? ResponseEntity.ok("Factura eliminada")
                : ResponseEntity.status(404).body("Factura no encontrada");
    }
}
