package com.example.tp2daos2020.controller;

import com.example.tp2daos2020.controller.error.MensajeError;
import com.example.tp2daos2020.dto.ClienteResponseDTO;
import com.example.tp2daos2020.dto.PedidoResponseDTO;
import com.example.tp2daos2020.entities.Cliente;
import com.example.tp2daos2020.entities.Pedido;
import com.example.tp2daos2020.service.ClienteServiceImpl;
import com.example.tp2daos2020.service.PedidoServiceImpl;
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
@RequestMapping(value="/api/pedido")
public class PedidoRestController {

    @Autowired
    private PedidoServiceImpl service;

    @Autowired
    private ClienteServiceImpl clienteService;

    /**
     * Permite filtrar entidades por id CLiente. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedido/searchByClient?id=55'
     * @param id
     * @return
     */
    @GetMapping(value="/searchByClient")
    public List<PedidoResponseDTO> filtrarCliente(@RequestParam(name = "id")  @javax.validation.constraints.Size(min = 1, max = 20) Long id) {
        List<Pedido> pedidos = service.showByClient(id);
        List<PedidoResponseDTO> objects=new ArrayList<PedidoResponseDTO>();
        for (Pedido pojo : pedidos)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Devuelve todos los elementos que no tengan soft-delete (campo borrado). Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedido'
     * @return
     */
    @GetMapping
    public List<Pedido> todos(){
        return service.all();
    }

    /**
     * Devuelve todos los registros, incluidos los que tengan soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedido/all'
     * @return
     */
    @GetMapping(value="/all")
    public List<Pedido> todosAdmin(){
        return service.allWithDeleted();
    }

    /**
     * Busca un objeto por su numero de id, no incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedido/show/99'
     * @param id
     * @return
     */
    @GetMapping(value="/show/{id}")
    public ResponseEntity<PedidoResponseDTO> mostrar(@PathVariable Long id){
        Optional<Pedido> rta = service.showById(id);
        if(rta.isPresent())
        {
            Pedido pojo=rta.get();
            return new ResponseEntity<PedidoResponseDTO>(buildResponse(pojo), HttpStatus.OK);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Busca un objeto por su numero de id, incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/ciudad/showWithDeleted/100'
     * @param id
     * @return
     */
    @GetMapping(value="/showWithDeleted/{id}")
    public Optional<Pedido> mostrarAdmin(@PathVariable Long id){
        Optional<Pedido> obj=service.showByIdWithDeleted(id);

        return obj;
    }

    /**
     * Persiste un objeto. Método POST
     * ej:
     * curl --location --request POST 'localhost:9088/api/pedido' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "total": 123.12,
     *     "cliente": {
     *             "id": 55
     *     }
     * }
     * '
     * @param form
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<Object> guardar( @Valid @RequestBody PedidoForm form, BindingResult result) throws Exception{
        if(result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
        Pedido p = form.toPojo();
        Optional<Cliente> c = clienteService.showById(form.getCliente().getId());
        if(c.isPresent())
            p.setCliente(c.get());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Cliente Requerido", "El cliente indicado no se encuentra en la base de datos."));
        service.insert(p);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(p.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

        return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
    }

    /**
     * Actualiza una entidad. Método PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/pedido/103' \
     * --header 'Content-Type: application/json' \
     * --data-raw '    {
     *         "id":103,
     *         "total": 999.99,
     *         "cliente": {
     *             "id":56
     *         }
     *     }'
     * @param form
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object>  actualizar(@RequestBody PedidoForm form, @PathVariable long id){
        Optional<Pedido> rta = service.showById(id);
        if(!rta.isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se el pedido que desea modificar.");
        else
        {
            Pedido p = form.toPojo();
            Optional<Cliente> c = clienteService.showById(form.getCliente().getId());
            if(c.isPresent())
                p.setCliente(c.get());
            else
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El cliente indicado no se encuentra en la base de datos.");

            p.setId(id);  //El id es el identificador, con lo cual es el único dato que no permito modificar
            service.update(p);
            return ResponseEntity.ok(buildResponse(p));
        }
    }

    /**
     * Realiza soft-delete sobre una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/pedido/102'
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
     * curl --location --request PUT 'localhost:9088/api/pedido/recycle/102'
     * @param id
     * @return
     */
    @PutMapping(value="/recycle/{id}")
    public Pedido reciclar(@PathVariable Long id){
        Optional<Pedido> obj=service.showByIdWithDeleted(id);
        service.recycle(obj.get());

        return obj.get();
    };

    /**
     * Realiza la destruccion de una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/pedido/destroy/102'
     * @param id
     */
    @DeleteMapping(value="/destroy/{id}")
    public void destruir(@PathVariable Long id){
        service.destroy(id);
    }

    /**
     * Métdo auxiliar que toma los datos del pojo y construye el DTO con los hipervinculos HATEOAS
     * ej:
     * @param pojo
     * @return
     */
    private PedidoResponseDTO buildResponse(Pedido pojo) {
        PedidoResponseDTO dto = new PedidoResponseDTO(pojo);
        //Self link
        Link selfLink = WebMvcLinkBuilder.linkTo(PedidoRestController.class)
                .slash(pojo.getId())
                .withSelfRel();
        //Method link: Link al servicio que permitirá navegar hacia la ciudad relacionada a la persona
        Link clienteLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteRestController.class)
                .mostrar(pojo.getCliente().getId()))
                .withRel("cliente");
        dto.add(selfLink);
        dto.add(clienteLink);
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
