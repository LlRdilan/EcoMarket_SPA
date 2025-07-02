package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.FacturaController;
import com.example.EcoMarket_SPA.Model.Factura;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class FacturaModelAssemblers implements RepresentationModelAssembler<Factura, EntityModel<Factura>> {

    @Override
    public @NonNull EntityModel<Factura> toModel(@NonNull Factura factura) {
        return EntityModel.of(factura,
                linkTo(methodOn(FacturaController.class).getFactura(factura.getId())).withSelfRel(),
                linkTo(methodOn(FacturaController.class).getFacturas()).withRel("all"));
    }
}
