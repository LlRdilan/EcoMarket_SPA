package com.example.EcoMarket_SPA.Services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Repository.ProductoRepository;

import java.util.List;

@Service
@Transactional
public class ProductoServices {

    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.getProductos();
    }

    public Producto getProducto(int id) {
        return productoRepository.getProducto(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.saveProducto(producto);
    }

    public String deleteProductoById(int id) {
        productoRepository.deleteProducto(id);
        return "Producto eliminado";
    }

    public Producto updateProductoById(int id, Producto producto) {
        Producto producto1 = getProducto(id);
        producto1.setNombre(producto.getNombre());
        producto1.setPrecio(producto.getPrecio());
        producto1.setDescripcion(producto.getDescripcion());
        producto1.setId(id);
        return producto1;
    }

}