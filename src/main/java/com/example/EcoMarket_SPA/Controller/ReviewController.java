package com.example.EcoMarket_SPA.Controller;

import com.example.EcoMarket_SPA.Model.Review;
import com.example.EcoMarket_SPA.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tiendas")
public class ReviewController {
    @Autowired
    private ReviewServices reviewServices;

    @GetMapping
    public List<Review> listarReviews() {
        return reviewServices.getReview();
    }

    @PostMapping
    public Review agregarReview(@RequestBody Review review) {
        return reviewServices.saveReview(review);
    }

    @GetMapping({"{id}"})
    public Review buscarReview(@PathVariable int id) {
        return reviewServices.getReviewId(id);
    }

    @PutMapping({"{id}"})
    public Review actualizarReview(@PathVariable int id, @RequestBody Review review) {
        return reviewServices.updateReview(review);
    }

    @DeleteMapping({"{id}"})
    public String eliminarReview(@PathVariable int id) {
        return reviewServices.deleteReview(id);
    }
}
