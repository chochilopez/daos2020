package com.example.tp2daos2020.controller;


import com.example.tp2daos2020.entities.Ciudad;
import com.example.tp2daos2020.entities.Cliente;
import com.example.tp2daos2020.entities.Pedido;

import javax.validation.constraints.NotNull;

public class PedidoForm {

    private Long id;
    private Double total;
    @NotNull(message = "El cliente no puede ser nulo")
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido toPojo(){
        Pedido pojo = new Pedido();
        pojo.setId(this.getId());
        pojo.setTotal(this.getTotal());
        pojo.setCliente(this.getCliente());

        return pojo;
    }
}
