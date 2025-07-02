package com.example.EcoMarket_SPA.Assemblers;

import com.example.EcoMarket_SPA.Controller.PedidoController;
import com.example.EcoMarket_SPA.Model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssemblers implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public @NonNull EntityModel<Pedido> toModel(@NonNull Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoController.class).getPedido(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).getPedidos()).withRel("all"));
    }
}
