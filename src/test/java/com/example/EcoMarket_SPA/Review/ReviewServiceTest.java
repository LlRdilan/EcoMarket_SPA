package com.example.EcoMarket_SPA.Review;

import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Model.Review;
import com.example.EcoMarket_SPA.Repository.ReviewRepository;
import com.example.EcoMarket_SPA.Services.ReviewServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServices reviewServices;

    private Review review;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Cliente cliente = new Cliente();
        Producto producto = new Producto();
        review = new Review(1, cliente, producto, 5, "Excelente", LocalDate.now());
    }

    @Test
    void testGetAllReviews() {
        when(reviewRepository.findAll()).thenReturn(List.of(review));
        List<Review> result = reviewServices.getAllReviews();
        assertFalse(result.isEmpty());
        assertEquals(5, result.get(0).getCalificacion());
    }

    @Test
    void testGetReviewById() {
        when(reviewRepository.findById(1)).thenReturn(Optional.of(review));
        Review result = reviewServices.getReview(1);
        assertNotNull(result);
        assertEquals("Excelente", result.getDescripcion());
    }

    @Test
    void testSaveReview() {
        when(reviewRepository.save(review)).thenReturn(review);
        Review result = reviewServices.saveReview(review);
        assertEquals(5, result.getCalificacion());
    }

    @Test
    void testDeleteReview() {
        when(reviewRepository.existsById(1)).thenReturn(true);
        doNothing().when(reviewRepository).deleteById(1);
        boolean result = reviewServices.deleteReview(1);
        assertTrue(result);
    }
}
