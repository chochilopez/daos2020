package com.example.tp2daos2020.controller;

import com.example.tp2daos2020.controller.error.MensajeError;
import com.example.tp2daos2020.dto.ClienteResponseDTO;
import com.example.tp2daos2020.entities.Ciudad;
import com.example.tp2daos2020.entities.Cliente;
import com.example.tp2daos2020.service.CiudadServiceImpl;
import com.example.tp2daos2020.service.ClienteServiceImpl;
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
@RequestMapping(value="/api/cliente")
public class ClienteRestController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private CiudadServiceImpl ciudadService;

    /**
     * Permite filtrar personas. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/cliente/search?apellido=Manning&&nombre=Wilma'
     * @param apellido
     * @param nombre
     * @return
     */
    @GetMapping(value="/search")
    public List<ClienteResponseDTO> filtrarPersonas(@RequestParam(name = "apellido") String apellido,
                                                    @RequestParam(name = "nombre")  String nombre) {
        List<Cliente> clientes = clienteService.search(apellido,nombre);
        List<ClienteResponseDTO> objects=new ArrayList<ClienteResponseDTO>();
        for (Cliente pojo : clientes)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Devuelve todos los elementos que no tengan soft-delete (campo borrado). Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/cliente'
     * @return
     */
    @GetMapping
    public List<Cliente> todos(){
        return clienteService.all();
    }

    /**
     * Devuelve todos los registros, incluidos los que tengan soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/cliente/all'
     * @return
     */
    @GetMapping(value="/all")
    public List<Cliente> todosAdmin(){
        return clienteService.allWithDeleted();
    }

    /**
     * Busca un objeto por su numero de id, no incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/cliente/show/55'
     * @param id
     * @return
     */
    @GetMapping(value="/show/{id}")
    public ResponseEntity<ClienteResponseDTO> mostrar(@PathVariable Long id){
        Optional<Cliente> rta = clienteService.showById(id);
        if(rta.isPresent())
        {
            Cliente pojo=rta.get();
            return new ResponseEntity<ClienteResponseDTO>(buildResponse(pojo), HttpStatus.OK);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Busca un objeto por su numero de id, incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/cliente/showWithDeleted/56'
     * @param id
     * @return
     */
    @GetMapping(value="/showWithDeleted/{id}")
    public Optional<Cliente> mostrarAdmin(@PathVariable Long id){
        Optional<Cliente> obj=clienteService.showByIdWithDeleted(id);

        return obj;
    }

    /**
     * Persiste un objeto. Método POST
     * ej:
     * curl --location --request POST 'localhost:9088/api/cliente' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "nombre": "Gaston",
     *     "apellido": "Lopez",
     *     "ciudad": {
     *         "id": 201
     *     }
     * }'
     * @param form
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<Object> guardar(@Valid @RequestBody com.example.tp2daos2020.controller.ClienteForm form, BindingResult result) throws Exception{
        if(result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
        Cliente cliente = form.toPojo();
        Optional<Ciudad> ciudad = ciudadService.showById(form.getCiudad().getId());
        if(ciudad.isPresent())
            cliente.setCiudad(ciudad.get());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Ciudad Requerida", "La ciudad indicada no se encuentra en la base de datos."));
        clienteService.insert(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}")
                .buildAndExpand(cliente.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

        return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
    }

    /**
     * Actualiza una entidad. Método PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/cliente/102' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "nombre": "Pepito se mudo a crespo",
     *     "apellido": "Pepon",
     *     "ciudad": {
     *         "id": 201
     *     }
     * }'
     * @param form
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object>  actualizar(@RequestBody com.example.tp2daos2020.controller.ClienteForm form, @PathVariable long id)
    {
        Optional<Cliente> rta = clienteService.showById(id);
        if(!rta.isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra la persona que desea modificar.");

        else
        {
            Cliente p = form.toPojo();
            Optional<Ciudad> c = ciudadService.showById(form.getCiudad().getId());
            if(c.isPresent())
                p.setCiudad(c.get());
            else
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La ciudad indicada no se encuentra en la base de datos.");

            p.setId(id);  //El id es el identificador, con lo cual es el único dato que no permito modificar
            clienteService.update(p);
            return ResponseEntity.ok(buildResponse(p));
        }
    }

    /**
     * Realiza soft-delete sobre una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/cliente/102'
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id)
    {
        if(!clienteService.showById(id).isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe una persona con ese id");
        clienteService.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * Restaura una entidad que fue marcada con soft-delete. Metodo PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/cliente/recycle/102'
     * @param id
     * @return
     */
    @PutMapping(value="/recycle/{id}")
    public Cliente reciclar(@PathVariable Long id){
        Optional<Cliente> obj=clienteService.showByIdWithDeleted(id);
        clienteService.recycle(obj.get());

        return obj.get();
    };

    /**
     * Realiza la destruccion de una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/cliente/destroy/99'
     * @param id
     */
    @DeleteMapping(value="/destroy/{id}")
    public void destruir(@PathVariable Long id){
        clienteService.destroy(id);
    }

    /**
     * Métdo auxiliar que toma los datos del pojo y construye el DTO con los hipervinculos HATEOAS
     * ej:
     * @param pojo
     * @return
     */
    private ClienteResponseDTO buildResponse(Cliente pojo) {
        ClienteResponseDTO dto = new ClienteResponseDTO(pojo);
        //Self link
        Link selfLink = WebMvcLinkBuilder.linkTo(CiudadRestController.class)
                .slash(pojo.getId())
                .withSelfRel();
        //Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada a la persona
        Link ciudadLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CiudadRestController.class)
                .mostrar(pojo.getCiudad().getId()))
                .withRel("ciudad");
        dto.add(selfLink);
        dto.add(ciudadLink);
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
//		primero transformamos la lista de errores devuelta por Java Bean Validation
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
