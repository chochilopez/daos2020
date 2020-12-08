package com.example.tp2daos2020.controller;


import com.example.tp2daos2020.entities.Ciudad;

import javax.validation.constraints.NotNull;

public class CiudadForm {

    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;
    @NotNull(message = "El codigo postal no puede ser nulo")
    private Long cp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCp() {
        return cp;
    }

    public void setCp(Long cp) {
        this.cp = cp;
    }

    public Ciudad toPojo(){
        Ciudad pojo = new Ciudad();
        pojo.setId(this.getId());
        pojo.setNombre(this.getNombre());
        pojo.setCp(this.getCp());

        return pojo;
    }
}

