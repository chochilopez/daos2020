package com.example.tp2daos2020.service;

import com.example.tp2daos2020.dao.CiudadRepository;
import com.example.tp2daos2020.entities.Ciudad;
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
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository repositorio;

    @Autowired
    private Validator validator;

    /**
     * Muestra todos los registros que tengan el atributo borrado como null
     * @return Array de ciudades
     */
    @Override
    public List<Ciudad> all(){
        List<Ciudad> result = repositorio.all();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Ciudad>();
        }
    };

    /**
     * Muestra todos los registros
     * @return Array de ciudades
     */
    @Override
    public List<Ciudad>allWithDeleted(){
        List<Ciudad> result = repositorio.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Ciudad>();
        }
    };

    /**
     * Muestra una entidad, solo si existe y posee el atributo borrado como null
     * @param id
     * @return
     */
    @Override
    public Optional<Ciudad> showById(Long id){
        Optional<Ciudad> result=repositorio.show(id);

        return result;
    };

    /**
     * Muestra una entidad, solo si existe
     * @param id
     * @return
     */
    @Override
    public Optional<Ciudad> showByIdWithDeleted(Long id){
        Optional<Ciudad> result=repositorio.findById(id);

        return result;
    };

    /**
     * Persiste una nueva entidad
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Ciudad insert(Ciudad obj) throws Exception{
        Set<ConstraintViolation<Ciudad>> cv= validator.validate(obj);
        if (cv.size()>0){
            String error="";
            for (ConstraintViolation<Ciudad> constraintViolation : cv)
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
    public Ciudad update(Ciudad obj) {
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
        Optional<Ciudad> obj=repositorio.show(id);
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
    public Ciudad recycle(Ciudad obj){
        obj.setBorrado(null);
        obj=repositorio.save(obj);

        return obj;
    };

    /**
     * Busca ciudad por su nombre
     * @param nombre
     * @return
     */
    @Override
    public List<Ciudad> searchName(String nombre) {
        if(nombre==null)
            return repositorio.findAll();
        else
            return repositorio.findByName(nombre);
    }

    /**
     * Busca ciudad por su codigo postal
     * @param cp
     * @return
     */
    public List<Ciudad> searchCp(Long cp) {
        if(cp==null)
            return repositorio.findAll();
        else
            return repositorio.findByCp(cp);
    }
}
