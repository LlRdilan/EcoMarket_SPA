package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Proovedor;
import com.example.EcoMarket_SPA.Services.ProovedorServices;
import com.example.EcoMarket_SPA.Assemblers.ProovedorModelAssemblers;
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
@RequestMapping("/api/proovedores")
@Tag(name = "API Proovedores", description = "Servicios para gestión de proveedores")
public class ProovedorController {

    @Autowired
    private ProovedorServices proovedorServices;

    @Autowired
    private ProovedorModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar proveedores", description = "Retorna todos los proveedores registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedores encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay proveedores registrados")
    })
    public ResponseEntity<CollectionModel<EntityModel<Proovedor>>> getAllProovedores() {
        List<Proovedor> lista = proovedorServices.getProovedores();
        if (lista.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(lista));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener proveedor por ID", description = "Retorna un proveedor por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor encontrado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<EntityModel<Proovedor>> getProovedor(
            @Parameter(description = "ID del proveedor a buscar") @PathVariable int id) {

        Proovedor proovedor = proovedorServices.getProovedor(id);
        if (proovedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(proovedor));
    }

    @PostMapping
    @Operation(summary = "Crear proveedor", description = "Agrega un nuevo proveedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor creado exitosamente")
    })
    public ResponseEntity<Proovedor> crearProovedor(@RequestBody Proovedor proovedor) {
        return ResponseEntity.ok(proovedorServices.saveProovedor(proovedor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar proveedor", description = "Actualiza un proveedor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<Proovedor> actualizarProovedor(
            @Parameter(description = "ID del proveedor a actualizar") @PathVariable int id,
            @RequestBody Proovedor proovedor) {

        Proovedor actualizado = proovedorServices.updateProovedor(id, proovedor);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar proveedor", description = "Elimina un proveedor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    public ResponseEntity<String> eliminarProovedor(
            @Parameter(description = "ID del proveedor a eliminar") @PathVariable int id) {

        boolean eliminado = proovedorServices.deleteProovedor(id);
        return eliminado
                ? ResponseEntity.ok("Proveedor eliminado")
                : ResponseEntity.status(404).body("Proveedor no encontrado");
    }
}
