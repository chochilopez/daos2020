package com.example.daos2020.demo.service;


import com.example.daos2020.demo.dao.PedidoRepository;
import com.example.daos2020.demo.entities.Pedido;
import com.example.daos2020.demo.helpers.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repositorio;

    //FIXME toda la logica tiene que estar en el srvicio y no en el controlador
    @Override
    public List<Pedido> all(){
        List<Pedido> result = repositorio.all();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Pedido>();
        }
    };

    @Override
    public List<Pedido>allWithDeleted(){
        List<Pedido> result = repositorio.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Pedido>();
        }
    };

    @Override
    public Optional<Pedido> show(Long id){
        Optional<Pedido> result=repositorio.show(id);

        return result;
    };

    @Override
    public Optional<Pedido> showWithDeleted(Long id){
        Optional<Pedido> result=repositorio.findById(id);

        return result;
    };

    @Override
    public Pedido save(Pedido obj) {
        obj=repositorio.save(obj);

        return obj;
    }

    @Override
    public Pedido recycle(Pedido obj){
        obj.setBorrado(null);
        obj=repositorio.save(obj);

        return obj;
    };

    @Override
    public void delete(Long id){
        Optional<Pedido> obj=repositorio.show(id);
        obj.get().setBorrado(Helper.getToday());
        repositorio.save(obj.get());
    };

    @Override
    public void destroy(Long id){
        repositorio.deleteById(id);
    };

    @Override
    public long count(){
        return repositorio.count();
    };

}
