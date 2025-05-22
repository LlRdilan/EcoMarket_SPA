
package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Repository.ProovedorRepository;
import com.example.EcoMarket_SPA.Model.Proovedor;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ProovedorServices {

    private ProovedorRepository proovedorRepository;

    public Proovedor getProovedor(int id) {
        return proovedorRepository.getProovedor(id);
    }

    public String deleteProovedor(int id) {
        proovedorRepository.deleteProovedor(id);
        return "Proveedor eliminado";
    }
    //public Proovedor updateProovedor(Proovedor provedor) {}
}
