package com.example.tp2daos2020.controller;


import com.example.tp2daos2020.entities.Producto;

import javax.validation.constraints.NotNull;

public class ProductoForm {

    private Long id;
    @NotNull(message = "El modelo no puede ser nulo")
    private String modelo;
    @NotNull(message = "La marca no puede ser nula")
    private String marca;
    private Double precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Producto toPojo(){
        Producto pojo = new Producto();
        pojo.setId(this.getId());
        pojo.setMarca(this.getMarca());
        pojo.setModelo(this.getModelo());
        pojo.setPrecio(this.getPrecio());

        return pojo;
    }
}

