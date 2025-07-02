package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.ReviewController;
import com.example.EcoMarket_SPA.Model.Review;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReviewModelAssemblers implements RepresentationModelAssembler<Review, EntityModel<Review>> {

    @Override
    public @NonNull EntityModel<Review> toModel(@NonNull Review review) {
        return EntityModel.of(review,
                linkTo(methodOn(ReviewController.class).getReview(review.getId())).withSelfRel(),
                linkTo(methodOn(ReviewController.class).getAllReviews()).withRel("all"));
    }
}
