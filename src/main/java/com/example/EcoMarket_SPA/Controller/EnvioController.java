package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Envio;
import com.example.EcoMarket_SPA.Services.EnvioServices;
import com.example.EcoMarket_SPA.Assemblers.EnvioModelAssemblers;
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
@RequestMapping("/api/envios")
@Tag(name = "API Envíos", description = "Servicios para gestión de envíos")
public class EnvioController {

    @Autowired
    private EnvioServices envioServices;

    @Autowired
    private EnvioModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar envíos", description = "Retorna todos los envíos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envíos encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay envíos registrados")
    })
    public ResponseEntity<CollectionModel<EntityModel<Envio>>> getEnvios() {
        List<Envio> envios = envioServices.getEnvios();
        if (envios.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(envios));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener envío por ID", description = "Retorna un envío por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío encontrado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<EntityModel<Envio>> getEnvio(
            @Parameter(description = "ID del envío a buscar") @PathVariable int id) {

        Envio envio = envioServices.obtenerEnvio(id);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(envio));
    }

    @PostMapping
    @Operation(summary = "Agregar envío", description = "Agrega un nuevo envío")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío agregado exitosamente")
    })
    public ResponseEntity<Envio> crearEnvio(@RequestBody Envio envio) {
        return ResponseEntity.ok(envioServices.guardarEnvio(envio));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar envío", description = "Actualiza un envío existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío actualizado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<Envio> actualizarEnvio(
            @Parameter(description = "ID del envío a actualizar") @PathVariable int id,
            @RequestBody Envio envio) {

        envio.setId(id);
        Envio actualizado = envioServices.actualizarEnvio(envio);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar envío", description = "Elimina un envío por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío eliminado"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    public ResponseEntity<String> eliminarEnvio(
            @Parameter(description = "ID del envío a eliminar") @PathVariable int id) {

        boolean eliminado = envioServices.eliminarEnvio(id);
        return eliminado
                ? ResponseEntity.ok("Envío eliminado")
                : ResponseEntity.status(404).body("Envío no encontrado");
    }
}
