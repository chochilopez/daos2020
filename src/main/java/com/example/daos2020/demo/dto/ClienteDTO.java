package com.example.daos2020.demo.dto;

import com.example.daos2020.demo.controllerRest.ClienteControllerRest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

public class ClienteDTO extends RepresentationModel<ClienteDTO> {
    Link selfLink= WebMvcLinkBuilder.linkTo(ClienteControllerRest.class)
            .get
            .withSelfRel();
}

