package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Cupon;
import com.example.EcoMarket_SPA.Services.CuponServices;
import com.example.EcoMarket_SPA.Assemblers.CuponModelAssemblers;
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
@RequestMapping("/api/cupones")
@Tag(name = "API Cupones", description = "Servicios para gestión de cupones")
public class CuponController {

    @Autowired
    private CuponServices cuponServices;

    @Autowired
    private CuponModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar cupones", description = "Retorna todos los cupones registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupones encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay cupones registrados")
    })
    public ResponseEntity<CollectionModel<EntityModel<Cupon>>> getCupons() {
        // Verificar si la lista está vacía
        List<Cupon> cupones = cuponServices.getCupons();
        if (cupones.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(cupones));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cupón por ID", description = "Retorna un cupón por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupón encontrado"),
            @ApiResponse(responseCode = "404", description = "Cupón no encontrado")
    })
    public ResponseEntity<EntityModel<Cupon>> getCupon(
            @Parameter(description = "ID del cupón a buscar") @PathVariable int id) {

        Cupon cupon = cuponServices.obtenerCupon(id);
        if (cupon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(cupon));
    }

    @PostMapping
    @Operation(summary = "Agregar cupón", description = "Agrega un nuevo cupón")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupón agregado exitosamente")
    })
    public ResponseEntity<Cupon> crearCupon(@RequestBody Cupon cupon) {
        return ResponseEntity.ok(cuponServices.guardarCupon(cupon));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cupón", description = "Actualiza un cupón existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupón actualizado"),
            @ApiResponse(responseCode = "404", description = "Cupón no encontrado")
    })
    public ResponseEntity<Cupon> actualizarCupon(
            @Parameter(description = "ID del cupón a actualizar") @PathVariable int id,
            @RequestBody Cupon cupon) {

        cupon.setId(id);
        Cupon actualizado = cuponServices.actualizarCupon(cupon);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cupón", description = "Elimina un cupón por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupón eliminado"),
            @ApiResponse(responseCode = "404", description = "Cupón no encontrado")
    })
    public ResponseEntity<String> eliminarCupon(
            @Parameter(description = "ID del cupón a eliminar") @PathVariable int id) {

        boolean eliminado = cuponServices.eliminarCupon(id);
        return eliminado
                ? ResponseEntity.ok("Cupón eliminado")
                : ResponseEntity.status(404).body("Cupón no encontrado");
    }
}
