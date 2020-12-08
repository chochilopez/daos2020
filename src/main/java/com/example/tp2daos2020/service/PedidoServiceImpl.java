package com.example.tp2daos2020.service;

import com.example.tp2daos2020.dao.PedidoRepository;
import com.example.tp2daos2020.entities.Pedido;
import com.example.tp2daos2020.exceptions.Excepcion;
import com.example.tp2daos2020.helpers.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repositorio;

    @Autowired
    private Validator validator;

    /**
     * Muestra todos los registros que tengan el atributo borrado como null
     * @return Array de ciudades
     */
    @Override
    public List<Pedido> all(){
        List<Pedido> result = repositorio.all();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Pedido>();
        }
    };

    /**
     * Muestra todos los registros
     * @return Array de Pedidos
     */
    @Override
    public List<Pedido>allWithDeleted(){
        List<Pedido> result = repositorio.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Pedido>();
        }
    };

    /**
     * Muestra una entidad, solo si existe y posee el atributo borrado como null
     * @param id
     * @return
     */
    @Override
    public Optional<Pedido> showById(Long id){
        Optional<Pedido> result=repositorio.show(id);

        return result;
    };

    /**
     * Muestra una entidad, solo si existe y posee el atributo borrado como null
     * @param id Cliente
     * @return
     */
    @Override
    public List<Pedido> showByClient(Long id){
        List<Pedido> result = repositorio.showByClient(id);
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Pedido>();
        }
    };

    /**
     * Muestra una entidad, solo si existe
     * @param id
     * @return
     */
    @Override
    public Optional<Pedido> showByIdWithDeleted(Long id){
        Optional<Pedido> result=repositorio.findById(id);

        return result;
    };

    /**
     * Persiste una nueva entidad
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Pedido insert(Pedido obj) throws Exception{
        Set<ConstraintViolation<Pedido>> cv= validator.validate(obj);
        if (cv.size()>0){
            String error="";
            for (ConstraintViolation<Pedido> constraintViolation : cv)
                error+=constraintViolation.getPropertyPath()+": "+constraintViolation.getMessage()+"\n";
            throw new Excepcion(error, 400);
        }
        else
            obj=repositorio.save(obj);

        return obj;
    }

    /**
     * Actualiza una entidad
     * @param obj
     * @return
     */
    @Override
    public Pedido update(Pedido obj) {
        obj.setActualizado(Helper.getToday());
        obj=repositorio.save(obj);

        return obj;
    }

    /**
     * Realiza soft-delete sobre una entidad
     * @param id
     */
    @Override
    public void delete(Long id){
        Optional<Pedido> obj=repositorio.show(id);
        obj.get().setBorrado(Helper.getToday());

        repositorio.save(obj.get());
    };

    /**
     * Destruye una entidad
     * @param id
     */
    @Override
    public void destroy(Long id){
        repositorio.deleteById(id);
    };

    /**
     * Actualiza el atributo borrado a null, quita soft-delete
     * @param obj
     * @return
     */
    @Override
    public Pedido recycle(Pedido obj){
        obj.setBorrado(null);
        obj=repositorio.save(obj);

        return obj;
    };
}
