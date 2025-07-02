package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.TiendaController;
import com.example.EcoMarket_SPA.Model.Tienda;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TiendaModelAssemblers implements RepresentationModelAssembler<Tienda, EntityModel<Tienda>> {

    @Override
    public @NonNull EntityModel<Tienda> toModel(@NonNull Tienda tienda) {
        return EntityModel.of(tienda,
                linkTo(methodOn(TiendaController.class).getTienda(tienda.getId())).withSelfRel(),
                linkTo(methodOn(TiendaController.class).getTiendas()).withRel("all"));
    }
}
