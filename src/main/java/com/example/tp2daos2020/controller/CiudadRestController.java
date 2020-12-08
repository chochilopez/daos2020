package com.example.tp2daos2020.controller;

import com.example.tp2daos2020.controller.error.MensajeError;
import com.example.tp2daos2020.dto.CiudadResponseDTO;
import com.example.tp2daos2020.entities.Ciudad;
import com.example.tp2daos2020.service.CiudadServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/ciudad")
public class CiudadRestController {

    @Autowired
    private CiudadServiceImpl service;

    /**
     * Permite filtrar entidades por nombre. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/ciudad/searchName?nombre=Hisar'
     * @param nombre
     * @return
     */
    @GetMapping(value="/searchName")
    public List<CiudadResponseDTO> filtrarCiudadesNombre(@RequestParam(name = "nombre")  @javax.validation.constraints.Size(min = 1, max = 20) String nombre) {
        List<Ciudad> ciudades = service.searchName(nombre);
        List<CiudadResponseDTO> objects=new ArrayList<CiudadResponseDTO>();
        for (Ciudad pojo : ciudades)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Permite filtrar entidades por cp. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/ciudad/searchCp?cp3116='
     * @param cp
     * @return
     */
    @GetMapping(value="/searchCp")
    public List<CiudadResponseDTO> filtrarCiudadesCp(@RequestParam(name = "cp")  @javax.validation.constraints.Size(min = 1, max = 5) Long cp) {
        List<Ciudad> ciudades = service.searchCp(cp);
        List<CiudadResponseDTO> objects=new ArrayList<CiudadResponseDTO>();
        for (Ciudad pojo : ciudades)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Devuelve todos los elementos que no tengan soft-delete (campo borrado). Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/ciudad'
     * @return
     */
    @GetMapping
    public List<Ciudad> todos(){
        return service.all();
    }

    /**
     * Devuelve todos los registros, incluidos los que tengan soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/ciudad/all'
     * @return
     */
    @GetMapping(value="/all")
    public List<Ciudad> todosAdmin(){
        return service.allWithDeleted();
    }

    /**
     * Busca un objeto por su numero de id, no incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/ciudad/show/155'
     * @param id
     * @return
     */
    @GetMapping(value="/show/{id}")
    public ResponseEntity<CiudadResponseDTO> mostrar(@PathVariable Long id){
        Optional<Ciudad> rta = service.showById(id);
        if(rta.isPresent())
        {
            Ciudad pojo=rta.get();
            return new ResponseEntity<CiudadResponseDTO>(buildResponse(pojo), HttpStatus.OK);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Busca un objeto por su numero de id, incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/ciudad/showWithDeleted/160'
     * @param id
     * @return
     */
    @GetMapping(value="/showWithDeleted/{id}")
    public Optional<Ciudad> mostrarAdmin(@PathVariable Long id){
        Optional<Ciudad> obj=service.showByIdWithDeleted(id);

        return obj;
    }

    /**
     * Persiste un objeto. Método POST
     * ej:
     * curl --location --request POST 'localhost:9088/api/ciudad' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "cp": 3116,
     *     "nombre": "Crespo"
     * }'
     * @param form
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<Object> guardar(@Valid @RequestBody com.example.tp2daos2020.controller.CiudadForm form, BindingResult result) throws Exception{
        if(result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
        Ciudad ciudad = form.toPojo();
        service.insert(ciudad);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(ciudad.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

        return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
    }

    /**
     * Actualiza una entidad. Método PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/ciudad/202' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "id":202,
     *     "cp": 12345,
     *     "nombre": "CRESPO"
     * }'
     * @param form
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object>  actualizar(@RequestBody com.example.tp2daos2020.controller.CiudadForm form, @PathVariable long id)
    {
        Optional<Ciudad> rta = service.showById(id);
        if(!rta.isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra la persona que desea modificar.");

        else
        {
            Ciudad p = form.toPojo();
            service.update(p);
            return ResponseEntity.ok(buildResponse(p));
        }
    }

    /**
     * Realiza soft-delete sobre una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/ciudad/206'
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id)
    {
        if(!service.showById(id).isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe una ciudad con ese id");
        service.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * Restaura una entidad que fue marcada con soft-delete. Metodo PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/ciudad/recycle/206'
     * @param id
     * @return
     */
    @PutMapping(value="/recycle/{id}")
    public Ciudad reciclar(@PathVariable Long id){
        Optional<Ciudad> obj=service.showByIdWithDeleted(id);
        service.recycle(obj.get());

        return obj.get();
    };

    /**
     * Realiza la destruccion de una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/ciudad/destroy/205'
     * @param id
     */
    @DeleteMapping(value="/destroy/{id}")
    public void destruir(@PathVariable Long id){
        service.destroy(id);
    }

    /**
     * Métdo auxiliar que toma los datos del pojo y construye el DTO con los hipervinculos HATEOAS
     * @param pojo
     * @return
     */
    private CiudadResponseDTO buildResponse(Ciudad pojo) {
        CiudadResponseDTO dto = new CiudadResponseDTO(pojo);
        //Self link
        Link selfLink = WebMvcLinkBuilder.linkTo(CiudadRestController.class)
                .slash(pojo.getId())
                .withSelfRel();
        //Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada a la persona
        dto.add(selfLink);
        return dto;
    }


    /**
     *
     * @param result
     * @return
     * @throws JsonProcessingException
     */
    private String formatearError(BindingResult result) throws JsonProcessingException
    {
        //primero transformamos la lista de errores devuelta por Java Bean Validation
        List<Map<String, String>> errores= result.getFieldErrors().stream().map(err -> {
            Map<String, String> error= new HashMap<>();
            error.put(err.getField(),err.getDefaultMessage() );
            return error;
        }).collect(Collectors.toList());
        MensajeError e1=new MensajeError();
        e1.setCodigo("01");
        e1.setMensajes(errores);

        //ahora usamos la librería Jackson para pasar el objeto a json
        ObjectMapper maper = new ObjectMapper();
        String json = maper.writeValueAsString(e1);
        return json;
    }

    /**
     *
     * @param code
     * @param err
     * @param descr
     * @return
     * @throws JsonProcessingException
     */
    private String getError(String code, String err, String descr) throws JsonProcessingException
    {
        MensajeError e1=new MensajeError();
        e1.setCodigo(code);
        ArrayList<Map<String,String>> errores=new ArrayList<>();
        Map<String, String> error=new HashMap<String, String>();
        error.put(err, descr);
        errores.add(error);
        e1.setMensajes(errores);

        //ahora usamos la librería Jackson para pasar el objeto a json
        ObjectMapper maper = new ObjectMapper();
        String json = maper.writeValueAsString(err);
        return json;
    }
}
