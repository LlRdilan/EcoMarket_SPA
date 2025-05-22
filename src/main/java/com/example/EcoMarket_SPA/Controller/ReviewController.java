package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Review;
import com.example.EcoMarket_SPA.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewServices reviewServices;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewServices.getAllReviews();
    }

    @GetMapping("/{id}")
    public Review getReview(@PathVariable int id) {
        return reviewServices.getReview(id);
    }

    @PostMapping
    public Review crearReview(@RequestBody Review review) {
        return reviewServices.saveReview(review);
    }

    @PutMapping("/{id}")
    public Review actualizarReview(@PathVariable int id, @RequestBody Review review) {
        return reviewServices.updateReview(id, review);
    }

    @DeleteMapping("/{id}")
    public String eliminarReview(@PathVariable int id) {
        boolean eliminado = reviewServices.deleteReview(id);
        return eliminado ? "Review eliminada" : "Review no encontrada";
    }
}
