package com.example.EcoMarket_SPA.Repository;
import java.util.ArrayList;
import java.util.List;
public class InventarioRepository {
  private List<Inventario> inventario = new ArrayList<>();

    public List<Inventario> getInventario() {
        return inventario;
    }

    public Inventario getProducto(int id){
        for(Inventario i: inventario){
            if(i.getId()==id){
                return i;
            }
        }
        return null;
    }

    public Inventario createProducto(Inventario i){
        inventario.add(i);
        return i;
    }
    public boolean deleteProducto(int id){
        Inventario i = this.getInventario(id);
        if(i==null){
            return false;
        }else{
            inventario.remove(i);
            return true;
        }
    }
    public Inventario updateProducto(Inventario i) {
        for(Inventario i2 : inventario) {
            if (i.getId() == i2.getId()) {
                i2.setProducto(i.getProducto());
                i2.setTienda(i.getTienda());
                return i2;
            }
        }
        return null;
    }
    }
}
