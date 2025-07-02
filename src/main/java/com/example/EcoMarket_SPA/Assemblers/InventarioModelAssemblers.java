package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.InventarioController;
import com.example.EcoMarket_SPA.Model.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssemblers implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public @NonNull EntityModel<Inventario> toModel(@NonNull Inventario inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioController.class).getInventarioPorId(inventario.getId())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).getInventario()).withRel("all"));
    }
}
