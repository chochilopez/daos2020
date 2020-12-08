package com.example.tp2daos2020.dao;

import com.example.tp2daos2020.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("select obj from Pedido obj where obj.borrado is null ")
    public List<Pedido> all();

    @Query("select obj from Pedido obj where obj.borrado is null and obj.id=:id")
    public Optional<Pedido> show(@Param("id")Long id);

    @Query(value = "select * from pedido where borrado is null and cliente_id=:id",nativeQuery = true)
    public List<Pedido>  showByClient(@Param("id")Long id);
}
