package com.example.daos2020.demo.service;

import com.example.daos2020.demo.entities.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    public List<Pedido> all();

    public List<Pedido>allWithDeleted();

    public Optional<Pedido> show(Long id);

    public Optional<Pedido> showWithDeleted(Long id);

    public Pedido save(Pedido obj);

    public Pedido recycle(Pedido obj);

    public void delete(Long id);

    public void destroy(Long id);

    public long count();
}
