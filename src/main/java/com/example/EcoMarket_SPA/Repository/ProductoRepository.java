
package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
