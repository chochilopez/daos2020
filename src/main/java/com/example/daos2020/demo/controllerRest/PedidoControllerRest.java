package com.example.daos2020.demo.controllerRest;

import com.example.daos2020.demo.entities.Pedido;
import com.example.daos2020.demo.helpers.Helper;
import com.example.daos2020.demo.service.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/pedido")
public class PedidoControllerRest {

    @Autowired
    private PedidoServiceImpl service;

    @GetMapping(value="/all")
    public List<Pedido> getAll(){
        return service.all();
    }

    @GetMapping(value="/allWithDeleted")
    public List<Pedido> getAllWithDeleted(){
        return service.allWithDeleted();
    }

    @GetMapping(value="/show/{id}")
    public Optional<Pedido> show(@PathVariable Long id){
        Optional<Pedido> obj=service.show(id);

        return obj;
    }

    @GetMapping(value="/showWithDeleted/{id}")
    public Optional<Pedido> showWithDeleted(@PathVariable Long id){
        Optional<Pedido> obj=service.showWithDeleted(id);

        return obj;
    }

    @PostMapping(value="/save")
    public ResponseEntity<Pedido> save(@RequestBody Pedido entidad){
        Pedido obj=service.save(entidad);

        return new ResponseEntity<Pedido>(obj, HttpStatus.OK);
    }


    @GetMapping(value="/recycle/{id}")
    public Pedido recycle(@PathVariable Long id){
        Optional<Pedido> obj=service.showWithDeleted(id);
        service.recycle(obj.get());

        return obj.get();
    };

    @GetMapping(value="/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    };

    @GetMapping(value="/destroy/{id}")
    public void destroy(@PathVariable Long id){
        service.destroy(id);
    }

    @GetMapping(value="/count")
    public long count(){
        return service.count();
    };

}
