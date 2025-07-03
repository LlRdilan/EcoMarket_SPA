package com.example.EcoMarket_SPA.Review;

import com.example.EcoMarket_SPA.Assemblers.ReviewModelAssemblers;
import com.example.EcoMarket_SPA.Controller.ReviewController;
import com.example.EcoMarket_SPA.Model.Cliente;
import com.example.EcoMarket_SPA.Model.Producto;
import com.example.EcoMarket_SPA.Model.Review;
import com.example.EcoMarket_SPA.Services.ReviewServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewServices reviewServices;

    @MockBean
    private ReviewModelAssemblers assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllReviews() throws Exception {
        Review review = new Review(1, new Cliente(), new Producto(), 5, "Excelente", LocalDate.now());
        List<Review> reviews = List.of(review);

        when(reviewServices.getAllReviews()).thenReturn(reviews);
        when(assembler.toCollectionModel(reviews)).thenReturn(CollectionModel.of(List.of(EntityModel.of(review))));

        mockMvc.perform(get("/api/reviews"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Excelente")));
    }

    @Test
    void testGetReviewById() throws Exception {
        Review review = new Review(1, new Cliente(), new Producto(), 5, "Excelente", LocalDate.now());

        when(reviewServices.getReview(1)).thenReturn(review);
        when(assembler.toModel(review)).thenReturn(EntityModel.of(review));

        mockMvc.perform(get("/api/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.calificacion").value(5));
    }

    @Test
    void testCreateReview() throws Exception {
        Review review = new Review(1, new Cliente(), new Producto(), 5, "Excelente", LocalDate.now());

        when(reviewServices.saveReview(review)).thenReturn(review);

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(review)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion").value("Excelente"));
    }

    @Test
    void testDeleteReview() throws Exception {
        when(reviewServices.deleteReview(1)).thenReturn(true);

        mockMvc.perform(delete("/api/reviews/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Review eliminada"));
    }
}
