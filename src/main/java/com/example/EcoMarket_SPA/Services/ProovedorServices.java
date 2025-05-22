package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Proovedor;
import com.example.EcoMarket_SPA.Repository.ProovedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProovedorServices {

    @Autowired
    private ProovedorRepository proovedorRepository;

    public List<Proovedor> getProovedores() {
        return proovedorRepository.findAll();
    }

    public Proovedor getProovedor(int id) {
        return proovedorRepository.findById(id).orElse(null);
    }

    public Proovedor saveProovedor(Proovedor proovedor) {
        return proovedorRepository.save(proovedor);
    }

    public boolean deleteProovedor(int id) {
        if (proovedorRepository.existsById(id)) {
            proovedorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Proovedor updateProovedor(Proovedor proovedor) {
        if (proovedorRepository.existsById(proovedor.getId())) {
            return proovedorRepository.save(proovedor);
        }
        return null;
    }
}
