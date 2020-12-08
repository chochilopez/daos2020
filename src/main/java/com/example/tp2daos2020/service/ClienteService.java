package com.example.tp2daos2020.service;


import com.example.tp2daos2020.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public List<Cliente> all();

    public List<Cliente>allWithDeleted();

    public Optional<Cliente> showById(Long id);

    public Optional<Cliente> showByIdWithDeleted(Long id);

    public Cliente insert(Cliente obj) throws Exception;

    public Cliente update(Cliente obj);

    public Cliente recycle(Cliente obj);

    public void delete(Long id);

    public void destroy(Long id);

    public List<Cliente> search(String apellido, String nombre);
}
