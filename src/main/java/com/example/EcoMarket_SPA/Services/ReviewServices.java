package com.example.EcoMarket_SPA.Services;

import com.example.EcoMarket_SPA.Model.Review;
import com.example.EcoMarket_SPA.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServices {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReview(){
        return reviewRepository.getReviews();
    }

    public Review saveReview (Review review) {
        return reviewRepository.guardar(review);
    }

    public Review getReviewId(int id) {
        return reviewRepository.buscarPorId(id);
    }

    public Review updateReview(Review review){
        return reviewRepository.actualizar(review);
    }

    public String  deleteReview(int id) {
        reviewRepository.eliminar(id);
        return "Review Eliminada";
    }
}
