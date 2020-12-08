package com.example.tp2daos2020.service;


import com.example.tp2daos2020.entities.Ciudad;

import java.util.List;
import java.util.Optional;

public interface CiudadService {
    public List<Ciudad> all();

    public List<Ciudad>allWithDeleted();

    public Optional<Ciudad> showById(Long id);

    public Optional<Ciudad> showByIdWithDeleted(Long id);

    public Ciudad insert(Ciudad obj) throws Exception;

    public Ciudad update(Ciudad obj);

    public Ciudad recycle(Ciudad obj);

    public void delete(Long id);

    public void destroy(Long id);

    public List<Ciudad> searchName(String nombre);

    public List<Ciudad> searchCp(Long cp);
}
