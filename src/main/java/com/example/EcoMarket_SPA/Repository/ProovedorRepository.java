package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Proovedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProovedorRepository extends JpaRepository<Proovedor, Integer> {
    // puedes agregar métodos personalizados si los necesitas
}

