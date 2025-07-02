package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.EnvioController;
import com.example.EcoMarket_SPA.Model.Envio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EnvioModelAssemblers implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {

    @Override
    public @NonNull EntityModel<Envio> toModel(@NonNull Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioController.class).getEnvio(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioController.class).getEnvios()).withRel("envios"));
    }
}
