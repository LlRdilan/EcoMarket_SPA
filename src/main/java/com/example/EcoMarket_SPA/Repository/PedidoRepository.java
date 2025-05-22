package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}

