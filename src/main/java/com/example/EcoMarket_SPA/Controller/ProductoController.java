package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Assemblers.ProductoModelAssemblers;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Services.ProductoServices;
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


@RestController
@RequestMapping("/api/productos")
@Tag(name = "API Productos", description = "Servicios para productos ecológicos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @Autowired
    private ProductoModelAssemblers assembler;

    @GetMapping
    @Operation(summary = "Listar productos", description = "Retorna todos los productos disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados"),
            @ApiResponse(responseCode = "404", description = "No hay productos disponibles") // agregado
    })
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> getAllProductos() {
        // Verificar que la lista no esté vacía
        var productos = productoServices.getAllProductos();
        if (productos.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(assembler.toCollectionModel(productos));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener producto por ID", description = "Retorna un producto por su ID con enlaces HATEOAS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<EntityModel<Producto>> getProducto(
            @Parameter(description = "ID del producto a buscar") @PathVariable int id) {

        // Verificar si el producto existe
        Producto producto = productoServices.getProducto(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(producto));
    }

    @PostMapping
    @Operation(summary = "Agregar producto", description = "Agrega un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto agregado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto) {
        // Verificar que los datos no sean nulos o inválidos (ejemplo: nombre vacío)
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            return ResponseEntity.badRequest().body("Nombre del producto es requerido");
        }

        return ResponseEntity.ok(productoServices.saveProducto(producto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<?> updateProducto(
            @Parameter(description = "ID del producto a actualizar") @PathVariable int id,
            @RequestBody Producto producto) {

        // Verificar que los datos del producto sean válidos
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            return ResponseEntity.badRequest().body("Nombre del producto es requerido");
        }

        Producto actualizado = productoServices.updateProducto(id, producto);
        return (actualizado != null)
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).body("Producto no encontrado");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> deleteProducto(
            @Parameter(description = "ID del producto a eliminar") @PathVariable int id) {

        // Verificar si el producto fue eliminado correctamente
        boolean eliminado = productoServices.deleteProducto(id);
        return eliminado
                ? ResponseEntity.ok("Producto eliminado")
                : ResponseEntity.status(404).body("Producto no encontrado");
    }
}

