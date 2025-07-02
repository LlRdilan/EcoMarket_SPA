package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.ClienteController;
import com.example.EcoMarket_SPA.Model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssemblers implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {

    @Override
    public @NonNull EntityModel<Cliente> toModel(@NonNull Cliente cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteController.class).getCliente(cliente.getId())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).getClientes()).withRel("all"));
    }
}
