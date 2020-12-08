package com.example.tp2daos2020.dao;

import com.example.tp2daos2020.entities.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long> {

    @Query("select obj from PedidoProducto obj where obj.borrado is null ")
    public List<PedidoProducto> all();

    @Query("select obj from PedidoProducto obj where obj.borrado is null and obj.id=:id")
    public Optional<PedidoProducto> show(@Param("id")Long id);

    @Query(value = "select * from pedido_producto where borrado is null and pedido_id=:id",nativeQuery = true)
    public List<PedidoProducto>  showByPedido(@Param("id")Long id);

    @Query(value = "select * from pedido_producto where borrado is null and producto_id=:id",nativeQuery = true)
    public List<PedidoProducto>  showByProducto(@Param("id")Long id);
}
