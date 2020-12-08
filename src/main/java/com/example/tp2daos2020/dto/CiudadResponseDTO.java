package com.example.tp2daos2020.dto;

import com.example.tp2daos2020.entities.Ciudad;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

//Patrón de diseño Data Transfer Object
public class CiudadResponseDTO extends RepresentationModel<CiudadResponseDTO> {
    private Long id;
    private String nombre;
    private Long cp;
    private Date creado;
    private Date actualizado;
    private Date borrado;

    public CiudadResponseDTO(Ciudad pojo){
        super();
        this.id=pojo.getId();
        this.nombre=pojo.getNombre();
        this.cp=pojo.getCp();
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
        return "CiudadResponseDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cp=" + cp +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", borrado=" + borrado +
                '}';
    }
}

