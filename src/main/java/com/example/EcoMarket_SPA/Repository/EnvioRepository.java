package com.example.EcoMarket_SPA.Repository;


import com.example.EcoMarket_SPA.Model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnvioRepository extends JpaRepository<Envio, Integer> {

}
