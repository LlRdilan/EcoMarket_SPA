package com.example.EcoMarket_SPA.Repository;


import com.example.EcoMarket_SPA.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

}