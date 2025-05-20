package com.example.EcoMarket_SPA.Repository;

import java.util.ArrayList;
import java.util.List;
public class ProovedorRepository {
  private List<Proovedor> proovedor = new ArrayList<>();

    public List<Proovedor> getProveedores() {
        return proveedores;
    }

    public Proovedor getProovedor(int id){
        for(Proovedor pr: proveedores){
            if(pr.getId()==id){
                return pr;
            }
        }
        return null;
    }

    public Proovedor createProovedor(Proovedor pr){
        proveedores.add(pr);
        return pr;
    }
    public boolean deleteProovedor(int id){
        Proovedor pr = this.getProovedor(id);
        if(pr==null){
            return false;
        }else{
            proveedores.remove(pr);
            return true;
        }
    }
    public Proovedor updateProovedor(Proovedor pr) {
        for(Proovedor pr2 : proveedores) {
            if (pr.getId() == pr2.getId()) {
                pr2.setNombre(pr.getNombre());
                pr2.setContacto(pr.getContacto());
                pr2.setEmail(pr.getEmail());
                pr2.setProductosSuministrados(pr.getProductosSuministrados());
                return pr2;
            }
        }
        return null;
    }
}
