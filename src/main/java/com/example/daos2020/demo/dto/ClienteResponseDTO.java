package com.example.daos2020.demo.dto;

import com.example.daos2020.demo.entities.Cliente;
import org.springframework.hateoas.RepresentationModel;
import java.util.Date;

//Patrón de diseño Data Transfer Object
public class ClienteResponseDTO extends RepresentationModel<ClienteResponseDTO> {

    private Long id;
    private String nombre;
    private String apellido;
    private Date creado;
    private Date actualizado;
    private Date borrado;

    public ClienteResponseDTO(Cliente pojo){
        super();
        this.id=pojo.getId();
        this.nombre=pojo.getNombre();
        this.apellido= pojo.getApellido();
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
        return "ClienteResponseDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", borrado=" + borrado +
                '}';
    }
}

