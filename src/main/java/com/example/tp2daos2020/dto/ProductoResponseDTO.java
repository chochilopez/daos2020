package com.example.tp2daos2020.dto;

import com.example.tp2daos2020.entities.Producto;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

//Patrón de diseño Data Transfer Object
public class ProductoResponseDTO extends RepresentationModel<ProductoResponseDTO> {

    private Long id;
    private String marca;
    private String modelo;
    private Double precio;
    private Date creado;
    private Date actualizado;
    private Date borrado;

    public ProductoResponseDTO(Producto pojo){
        super();
        this.id=pojo.getId();
        this.marca=pojo.getMarca();
        this.modelo= pojo.getModelo();
        this.precio= pojo.getPrecio();
        this.creado=pojo.getCreado();
        this.actualizado=pojo.getActualizado();
        this.borrado=pojo.getBorrado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }

    public Date getBorrado() {
        return borrado;
    }

    public void setBorrado(Date borrado) {
        this.borrado = borrado;
    }

    @Override
    public String toString() {
        return "ProductoResponseDTO{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", precio=" + precio +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", borrado=" + borrado +
                '}';
    }
}

