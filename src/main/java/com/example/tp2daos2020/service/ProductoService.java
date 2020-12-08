package com.example.tp2daos2020.service;


import com.example.tp2daos2020.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> all();

    public List<Producto>allWithDeleted();

    public Optional<Producto> showById(Long id);

    public Optional<Producto> showByIdWithDeleted(Long id);

    public Producto insert(Producto obj) throws Exception;

    public Producto update(Producto obj);

    public Producto recycle(Producto obj);

    public void delete(Long id);

    public void destroy(Long id);

    public List<Producto> searchBrand(String marca);

    public List<Producto> searchModel(String modelo);
}
