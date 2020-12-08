package com.example.tp2daos2020.service;


import com.example.tp2daos2020.entities.PedidoProducto;

import java.util.List;
import java.util.Optional;

public interface PedidoProductoService {
    public List<PedidoProducto> all();

    public List<PedidoProducto>allWithDeleted();

    public Optional<PedidoProducto> showById(Long id);

    public Optional<PedidoProducto> showByIdWithDeleted(Long id);

    public PedidoProducto insert(PedidoProducto obj) throws Exception;

    public PedidoProducto update(PedidoProducto obj);

    public PedidoProducto recycle(PedidoProducto obj);

    public void delete(Long id);

    public void destroy(Long id);

    public List<PedidoProducto> showByPedido(Long id);

    public List<PedidoProducto> showByProducto(Long id);
}
