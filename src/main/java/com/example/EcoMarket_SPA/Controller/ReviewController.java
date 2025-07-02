package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Review;
import com.example.EcoMarket_SPA.Services.ReviewServices;
import com.example.EcoMarket_SPA.Assemblers.ReviewModelAssemblers;
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
@RequestMapping("/api/reviews")
@Tag(name = "API Reviews", description = "Servicios para gestión de reviews de productos")
public class ReviewController {

    @Autowired
    private ReviewServices reviewServices;

    @Autowired
    private ReviewModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar reviews", description = "Retorna todas las reviews registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews encontradas"),
            @ApiResponse(responseCode = "404", description = "No hay reviews registradas")
    })
    public ResponseEntity<CollectionModel<EntityModel<Review>>> getAllReviews() {
        List<Review> reviews = reviewServices.getAllReviews();
        if (reviews.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(reviews));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener review por ID", description = "Retorna una review por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review encontrada"),
            @ApiResponse(responseCode = "404", description = "Review no encontrada")
    })
    public ResponseEntity<EntityModel<Review>> getReview(
            @Parameter(description = "ID de la review a buscar") @PathVariable int id) {

        Review review = reviewServices.getReview(id);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(review));
    }

    @PostMapping
    @Operation(summary = "Crear review", description = "Agrega una nueva review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review creada exitosamente")
    })
    public ResponseEntity<Review> crearReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewServices.saveReview(review));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar review", description = "Actualiza una review existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review actualizada"),
            @ApiResponse(responseCode = "404", description = "Review no encontrada")
    })
    public ResponseEntity<Review> actualizarReview(
            @Parameter(description = "ID de la review a actualizar") @PathVariable int id,
            @RequestBody Review review) {

        Review actualizada = reviewServices.updateReview(id, review);
        return (actualizada != null)
                ? ResponseEntity.ok(actualizada)
                : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar review", description = "Elimina una review por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review eliminada"),
            @ApiResponse(responseCode = "404", description = "Review no encontrada")
    })
    public ResponseEntity<String> eliminarReview(
            @Parameter(description = "ID de la review a eliminar") @PathVariable int id) {

        boolean eliminado = reviewServices.deleteReview(id);
        return eliminado
                ? ResponseEntity.ok("Review eliminada")
                : ResponseEntity.status(404).body("Review no encontrada");
    }
}
