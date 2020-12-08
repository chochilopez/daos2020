package com.example.tp2daos2020.dao;

import com.example.tp2daos2020.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("select obj from Producto obj where obj.borrado is null ")
    public List<Producto> all();

//    @Query(value = "select * from cliente where borrado is null and id_cliente=:id",nativeQuery = true)
//    public Optional<Cliente> show(@Param("id")Long id);

    @Query("select obj from Producto obj where obj.borrado is null and obj.id=:id")
    public Optional<Producto> show(@Param("id")Long id);

    @Query("select obj from Producto obj where obj.borrado is null and obj.modelo=:modelo")
    public List<Producto> findByModel(@Param("modelo")String modelo);

    @Query("select obj from Producto obj where obj.borrado is null and obj.marca=:marca")
    public List<Producto> findByBrand(@Param("marca")String marca);
}
