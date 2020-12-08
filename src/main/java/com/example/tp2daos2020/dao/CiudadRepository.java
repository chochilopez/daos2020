package com.example.tp2daos2020.dao;

import com.example.tp2daos2020.entities.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    @Query("select obj from Ciudad obj where obj.borrado is null ")
    public List<Ciudad> all();

//    @Query(value = "select * from ciudad where borrado is null and id_ciudad=:id",nativeQuery = true)
//    public Optional<Ciudad> show(@Param("id")Long id);

    @Query("select obj from Ciudad obj where obj.borrado is null and obj.id=:id")
    public Optional<Ciudad> show(@Param("id")Long id);

    @Query("select obj from Ciudad obj where obj.borrado is null and obj.nombre=:nombre")
    public List<Ciudad> findByName(@Param("nombre")String nombre);

    @Query("select obj from Ciudad obj where obj.borrado is null and obj.cp=:cp")
    public List<Ciudad>  findByCp(@Param("cp")Long cp);

}
