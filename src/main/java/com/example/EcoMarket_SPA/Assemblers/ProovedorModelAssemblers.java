package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.ProovedorController;
import com.example.EcoMarket_SPA.Model.Proovedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProovedorModelAssemblers implements RepresentationModelAssembler<Proovedor, EntityModel<Proovedor>> {

    @Override
    public @NonNull EntityModel<Proovedor> toModel(@NonNull Proovedor proovedor) {
        return EntityModel.of(proovedor,
                linkTo(methodOn(ProovedorController.class).getProovedor(proovedor.getId())).withSelfRel(),
                linkTo(methodOn(ProovedorController.class).getAllProovedores()).withRel("all"));
    }
}
