package com.example.tp2daos2020.controller;


import com.example.tp2daos2020.entities.Ciudad;
import com.example.tp2daos2020.entities.Cliente;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteForm {

    private Long id;
    @NotNull
    @Size(min=2, max=30)
    private String apellido;
    @NotNull
    @Size(min=2, max=30)
    private String nombre;
    @NotNull
    private Ciudad ciudad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Cliente toPojo(){
        Cliente pojo = new Cliente();
        pojo.setNombre(this.getNombre());
        pojo.setApellido(this.getApellido());
        pojo.setCiudad(this.getCiudad());

        return pojo;
    }
}

