package com.example.tp2daos2020.service;


import com.example.tp2daos2020.entities.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    public List<Pedido> all();

    public List<Pedido>allWithDeleted();

    public Optional<Pedido> showById(Long id);

    public List<Pedido>  showByClient(Long id);

    public Optional<Pedido> showByIdWithDeleted(Long id);

    public Pedido insert(Pedido obj) throws Exception;

    public Pedido update(Pedido obj);

    public Pedido recycle(Pedido obj);

    public void delete(Long id);

    public void destroy(Long id);
}
