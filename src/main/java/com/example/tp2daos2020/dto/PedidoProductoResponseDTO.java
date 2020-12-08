package com.example.tp2daos2020.dto;

import com.example.tp2daos2020.entities.Pedido;
import com.example.tp2daos2020.entities.PedidoProducto;
import com.example.tp2daos2020.entities.Producto;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

//Patrón de diseño Data Transfer Object
public class PedidoProductoResponseDTO extends RepresentationModel<PedidoProductoResponseDTO> {

    private Long id;
    private Double total;
    private Pedido pedido;
    private Producto producto;
    private Date creado;
    private Date actualizado;
    private Date borrado;

    public PedidoProductoResponseDTO(PedidoProducto pojo){
        super();
        this.id=pojo.getId();
        this.total=pojo.getTotal();
        this.producto= pojo.getProducto();
        this.pedido=pojo.getPedido();
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "PedidoProductoResponseDTO{" +
                "id=" + id +
                ", total=" + total +
                ", pedido=" + pedido +
                ", producto=" + producto +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                ", borrado=" + borrado +
                '}';
    }
}

