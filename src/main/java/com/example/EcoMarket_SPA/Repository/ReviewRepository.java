package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
