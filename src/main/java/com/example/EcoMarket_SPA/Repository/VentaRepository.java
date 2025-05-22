package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
