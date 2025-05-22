
package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Proovedor;
import java.util.ArrayList;
import java.util.List;

public class ProovedorRepository {
    private List<Proovedor> proovedor = new ArrayList<>();
    private List<Proovedor> proovedores;

    public List<Proovedor> getProveedores() {
        return proovedores;
    }

    public Proovedor getProovedor(int id){
        for(Proovedor pr: proovedores){
            if(pr.getId()==id){
                return pr;
            }
        }
        return null;
    }

    public Proovedor createProovedor(Proovedor pr){
        proovedores.add(pr);
        return pr;
    }
    public boolean deleteProovedor(int id){
        Proovedor pr = this.getProovedor(id);
        if(pr==null){
            return false;
        }else{
            proovedores.remove(pr);
            return true;
        }
    }
    public Proovedor updateProovedor(Proovedor pr) {
        for(Proovedor pr2 : proovedores) {
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
