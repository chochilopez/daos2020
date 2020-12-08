package com.example.tp2daos2020.controller;


import com.example.tp2daos2020.entities.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PedidoProductoForm {

    private Long id;
    @NotNull
    private Integer cantidad;
    @NotNull
    private Double total;
    @NotNull
    private Pedido pedido;
    @NotNull
    private Producto producto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public PedidoProducto toPojo(){
        PedidoProducto pojo = new PedidoProducto();
        pojo.setId(this.getId());
        pojo.setProducto(this.getProducto());
        pojo.setPedido(this.getPedido());
        pojo.setCantidad(this.getCantidad());
        pojo.setTotal(this.getTotal());

        return pojo;
    }
}

