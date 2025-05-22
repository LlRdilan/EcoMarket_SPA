package com.example.EcoMarket_SPA.Repository;
import java.util.List;
import java.util.ArrayList;
import com.example.EcoMarket_SPA.Model.Producto;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepository {
        private List<Producto> productos = new ArrayList<>();

        public List<Producto> getProductos() {
            return productos;
        }

        public Producto getProducto(int id){
            for(Producto p: productos){
                if(p.getId()==id){
                    return p;
                }
            }
            return null;
        }

        public Producto createProducto(Producto p){
            productos.add(p);
            return p;
        }
        public boolean deleteProducto(int id){
            Producto p = this.getProducto(id);
            if(p==null){
                return false;
            }else{
                productos.remove(p);
                return true;
            }
        }
        public Producto saveProducto(Producto p){
            return this.createProducto(p);
        }
        public Producto updateProducto(Producto p) {
            for(Producto p2 : productos) {
                if (p.getId() == p2.getId()) {
                    p2.setNombre(p.getNombre());
                    p2.setPrecio(p.getPrecio());
                    p2.setDescripcion(p.getDescripcion());
                    p2.setCantidad(p.getCantidad());
                    return p2;
                }
            }
            return null;
        }
    }
