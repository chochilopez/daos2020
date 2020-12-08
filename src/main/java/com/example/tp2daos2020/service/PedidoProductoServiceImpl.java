package com.example.tp2daos2020.service;

import com.example.tp2daos2020.dao.PedidoProductoRepository;
import com.example.tp2daos2020.entities.Cliente;
import com.example.tp2daos2020.entities.PedidoProducto;
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
public class PedidoProductoServiceImpl implements PedidoProductoService {

    @Autowired
    PedidoProductoRepository repositorio;
    @Autowired
    Validator validator;

    /**
     * Muestra todos los registros que tengan el atributo borrado como null
     * @return Array de ciudades
     */
    @Override
    public List<PedidoProducto> all(){
        List<PedidoProducto> result = repositorio.all();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<PedidoProducto>();
        }
    };

    /**
     * Muestra todos los registros
     * @return Array de ciudades
     */
    @Override
    public List<PedidoProducto>allWithDeleted(){
        List<PedidoProducto> result = repositorio.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<PedidoProducto>();
        }
    };

    /**
     * Muestra una entidad, solo si existe y posee el atributo borrado como null
     * @param id
     * @return
     */
    @Override
    public Optional<PedidoProducto> showById(Long id){
        Optional<PedidoProducto> result=repositorio.show(id);

        return result;
    };

    /**
     * Muestra una entidad, solo si existe
     * @param id
     * @return
     */
    @Override
    public Optional<PedidoProducto> showByIdWithDeleted(Long id){
        Optional<PedidoProducto> result=repositorio.findById(id);

        return result;
    };

    /**
     * Persiste una nueva entidad
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public PedidoProducto insert(PedidoProducto obj) throws Exception{
        Set<ConstraintViolation<PedidoProducto>> cv= validator.validate(obj);
        if (cv.size()>0){
            String error="";
            for (ConstraintViolation<PedidoProducto> constraintViolation : cv)
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
    public PedidoProducto update(PedidoProducto obj) {
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
        Optional<PedidoProducto> obj=repositorio.show(id);
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
    public PedidoProducto recycle(PedidoProducto obj){
        obj.setBorrado(null);
        obj=repositorio.save(obj);

        return obj;
    };

    @Override
    public List<PedidoProducto> showByPedido(Long id) {
        if(id==null)
            return repositorio.findAll();
        else
            return repositorio.showByPedido(id);
    }

    @Override
    public List<PedidoProducto> showByProducto(Long id) {
        if(id==null)
            return repositorio.findAll();
        else
            return repositorio.showByProducto(id);
    }
}
