package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.CuponController;
import com.example.EcoMarket_SPA.Model.Cupon;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CuponModelAssemblers implements RepresentationModelAssembler<Cupon, EntityModel<Cupon>> {

    @Override
    public @NonNull EntityModel<Cupon> toModel(@NonNull Cupon cupon) {
        return EntityModel.of(cupon,
                linkTo(methodOn(CuponController.class).getCupon(cupon.getId())).withSelfRel(),
                linkTo(methodOn(CuponController.class).getCupons()).withRel("all"));
    }
}
