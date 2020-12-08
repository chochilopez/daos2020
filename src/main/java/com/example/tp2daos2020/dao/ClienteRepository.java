package com.example.tp2daos2020.dao;

import com.example.tp2daos2020.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select obj from Cliente obj where obj.borrado is null ")
    public List<Cliente> all();

//    @Query(value = "select * from cliente where borrado is null and id_cliente=:id",nativeQuery = true)
//    public Optional<Cliente> show(@Param("id")Long id);

    @Query("select obj from Cliente obj where obj.borrado is null and obj.id=:id")
    public Optional<Cliente> show(@Param("id")Long id);

    @Query("select obj from Cliente obj where obj.borrado is null and obj.nombre=:nombre and obj.apellido=:apellido")
    public List<Cliente> findByApellidoNombre(@Param("apellido")String apellido, @Param("nombre")String nombre);

}
