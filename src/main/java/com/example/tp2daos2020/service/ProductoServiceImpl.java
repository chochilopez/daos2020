package com.example.tp2daos2020.service;

import com.example.tp2daos2020.dao.ProductoRepository;
import com.example.tp2daos2020.entities.Producto;
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
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repositorio;

    @Autowired
    private Validator validator;

    /**
     * Muestra todos los registros que tengan el atributo borrado como null
     * @return Array de Productos
     */
    @Override
    public List<Producto> all(){
        List<Producto> result = repositorio.all();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Producto>();
        }
    };

    /**
     * Muestra todos los registros
     * @return Array de Productos
     */
    @Override
    public List<Producto>allWithDeleted(){
        List<Producto> result = repositorio.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Producto>();
        }
    };

    /**
     * Muestra una entidad, solo si existe y posee el atributo borrado como null
     * @param id
     * @return
     */
    @Override
    public Optional<Producto> showById(Long id){
        Optional<Producto> result=repositorio.show(id);

        return result;
    };

    /**
     * Muestra una entidad, solo si existe
     * @param id
     * @return
     */
    @Override
    public Optional<Producto> showByIdWithDeleted(Long id){
        Optional<Producto> result=repositorio.findById(id);

        return result;
    };

    /**
     * Persiste una nueva entidad
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Producto insert(Producto obj) throws Exception{
        Set<ConstraintViolation<Producto>> cv= validator.validate(obj);
        if (cv.size()>0){
            String error="";
            for (ConstraintViolation<Producto> constraintViolation : cv)
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
    public Producto update(Producto obj) {
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
        Optional<Producto> obj=repositorio.show(id);
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
    public Producto recycle(Producto obj){
        obj.setBorrado(null);
        obj=repositorio.save(obj);

        return obj;
    };

    /**
     * Busca Productos por marca
     * @param marca
     * @return
     */
    @Override
    public List<Producto> searchBrand(String marca) {
        if(marca==null)
            return repositorio.findAll();
        else
            return repositorio.findByBrand(marca);
    }

    /**
     * Busca producto por su modelo
     * @param modelo
     * @return
     */
    public List<Producto> searchModel(String modelo) {
        if(modelo==null)
            return repositorio.findAll();
        else
            return repositorio.findByModel(modelo);
    }
}
