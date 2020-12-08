package com.example.tp2daos2020.service;

import com.example.tp2daos2020.dao.ClienteRepository;
import com.example.tp2daos2020.entities.Cliente;
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
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository repositorio;
    @Autowired
    Validator validator;

    /**
     * Muestra todos los registros que tengan el atributo borrado como null
     * @return Array de ciudades
     */
    @Override
    public List<Cliente> all(){
        List<Cliente> result = repositorio.all();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Cliente>();
        }
    };

    /**
     * Muestra todos los registros
     * @return Array de ciudades
     */
    @Override
    public List<Cliente>allWithDeleted(){
        List<Cliente> result = repositorio.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Cliente>();
        }
    };

    /**
     * Muestra una entidad, solo si existe y posee el atributo borrado como null
     * @param id
     * @return
     */
    @Override
    public Optional<Cliente> showById(Long id){
        Optional<Cliente> result=repositorio.show(id);

        return result;
    };

    /**
     * Muestra una entidad, solo si existe
     * @param id
     * @return
     */
    @Override
    public Optional<Cliente> showByIdWithDeleted(Long id){
        Optional<Cliente> result=repositorio.findById(id);

        return result;
    };

    /**
     * Persiste una nueva entidad
     * @param obj
     * @return
     * @throws Exception
     */
    @Override
    public Cliente insert(Cliente obj) throws Exception{
        Set<ConstraintViolation<Cliente>> cv= validator.validate(obj);
        if (cv.size()>0){
            String error="";
            for (ConstraintViolation<Cliente> constraintViolation : cv)
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
    public Cliente update(Cliente obj) {
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
        Optional<Cliente> obj=repositorio.show(id);
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
    public Cliente recycle(Cliente obj){
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
    public List<Cliente> search(String nombre, String apellido) {
        if(nombre==null && apellido==null)
            return repositorio.findAll();
        else
            return repositorio.findByApellidoNombre(nombre, apellido);
    }
}
