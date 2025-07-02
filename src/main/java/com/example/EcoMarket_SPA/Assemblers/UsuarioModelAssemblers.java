package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.UsuarioController;
import com.example.EcoMarket_SPA.Model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssemblers implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public @NonNull EntityModel<Usuario> toModel(@NonNull Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuario(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getUsuarios()).withRel("all"));
    }
}
