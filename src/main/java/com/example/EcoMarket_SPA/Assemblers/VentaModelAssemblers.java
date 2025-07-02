package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.VentaController;
import com.example.EcoMarket_SPA.Model.Venta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VentaModelAssemblers implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {

    @Override
    public @NonNull EntityModel<Venta> toModel(@NonNull Venta venta) {
        return EntityModel.of(venta,
                linkTo(methodOn(VentaController.class).getVenta(venta.getId())).withSelfRel(),
                linkTo(methodOn(VentaController.class).getVentas()).withRel("all"));
    }
}
