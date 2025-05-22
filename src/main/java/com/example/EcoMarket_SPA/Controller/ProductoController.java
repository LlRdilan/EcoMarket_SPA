package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Services.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoServices productoServices;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoServices.getAllProductos();
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable int id) {
        return productoServices.getProducto(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoServices.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        return productoServices.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable int id) {
        boolean eliminado = productoServices.deleteProducto(id);
        return eliminado ? "Producto eliminado" : "Producto no encontrado";
    }
}
