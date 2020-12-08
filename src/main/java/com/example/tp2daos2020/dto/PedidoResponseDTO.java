package com.example.tp2daos2020.dto;

import com.example.tp2daos2020.entities.Cliente;
import com.example.tp2daos2020.entities.Pedido;
import com.example.tp2daos2020.entities.PedidoProducto;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

//Patrón de diseño Data Transfer Object
public class PedidoResponseDTO extends RepresentationModel<PedidoResponseDTO> {

    private Long id;
    private Double total;
    private Date creado;
    private Date actualizado;
    private Date borrado;
    private Cliente cliente;

    public PedidoResponseDTO(Pedido pojo){
        super();
        this.id=pojo.getId();
        this.total=pojo.getTotal();
        this.cliente= pojo.getCliente();
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "PedidoResponseDTO{" +
                "id=" + id +
                ", total=" + total +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", borrado=" + borrado +
                ", cliente=" + cliente +
                '}';
    }
}

