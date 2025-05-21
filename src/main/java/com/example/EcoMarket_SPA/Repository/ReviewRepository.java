package com.example.EcoMarket_SPA.Repository;

import com.example.EcoMarket_SPA.Model.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepository {
    private ArrayList<Review> reviews = new ArrayList<>();

    //Metodo que retornara todas las ventas
    public List<Review> getReviews() {
        return reviews;
    }

    //Metodo que mostrara el libro segun su id
    public Review buscarPorId(int id) {
        for (Review review : reviews) {
            if (review.getId() == id) {
                return review;
            }
        }
        return null;
    }

    //Metodo que guardara una venta
    public Review guardar(Review review) {
        reviews.add(review);
        return review;
    }

    //Metodo que actualizara la venta
    public Review actualizar(Review review) {
        int id = 0;
        int idPosition = 0;

        for(int i = 0; i < reviews.size(); i++) {
            if(reviews.get(i).getId() == review.getId()) {
                id = review.getId();
                idPosition = i;
            }
        }

        Review review1 = new Review();
        review1.setId(id);
        review1.setCliente(review.getCliente());
        review1.setProducto(review.getProducto());
        review1.setCalificacion(review.getCalificacion());
        review1.setDescripcion(review.getDescripcion());
        review1.setFecha(review.getFecha());

        reviews.set(idPosition, review1);
        return review1;
    }

    //Metodo que eliminara por id
    public void eliminar(int id){
        Review review = buscarPorId(id);
        if (review != null){
            reviews.remove(review);
        }
    }
}
