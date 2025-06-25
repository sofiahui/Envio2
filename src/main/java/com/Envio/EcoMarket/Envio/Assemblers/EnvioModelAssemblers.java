package com.Envio.EcoMarket.Envio.Assemblers;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.Envio.EcoMarket.Envio.Controller.EnvioControllerV2;
import com.Envio.EcoMarket.Envio.Modelo.Envio;


@Component
public class EnvioModelAssemblers implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {

    @Override
    @NonNull
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioControllerV2.class).getEnvioById(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioControllerV2.class).getAllEnvios()).withRel("envios"));
    }
}