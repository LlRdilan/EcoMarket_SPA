package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Integer> {


}
