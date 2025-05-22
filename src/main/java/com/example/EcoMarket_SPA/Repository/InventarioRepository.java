package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

}
