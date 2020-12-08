package com.example.tp2daos2020.controller;

import com.example.tp2daos2020.controller.error.MensajeError;
import com.example.tp2daos2020.dto.ProductoResponseDTO;
import com.example.tp2daos2020.entities.Producto;
import com.example.tp2daos2020.service.ProductoServiceImpl;
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
@RequestMapping(value="/api/producto")
public class ProductoRestController {

    @Autowired
    private ProductoServiceImpl service;

    /**
     * Permite filtrar entidades por marca. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/producto/searchBrand?marca=Duis'
     * @param marca
     * @return
     */
    @GetMapping(value="/searchBrand")
    public List<ProductoResponseDTO> filtrarMarca(@RequestParam(name = "marca")  @javax.validation.constraints.Size(min = 1, max = 20) String marca) {
        List<Producto> productos = service.searchBrand(marca);
        List<ProductoResponseDTO> objects=new ArrayList<ProductoResponseDTO>();
        for (Producto pojo : productos)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Permite filtrar entidades por modelo. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/producto/searchModel?modelo=sit'
     * @param modelo
     * @return
     */
    @GetMapping(value="/searchModel")
    public List<ProductoResponseDTO> filtrarModelo(@RequestParam(name = "modelo")  @javax.validation.constraints.Size(min = 1, max = 20) String modelo) {
        List<Producto> productos = service.searchBrand(modelo);
        List<ProductoResponseDTO> objects=new ArrayList<ProductoResponseDTO>();
        for (Producto pojo : productos)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Devuelve todos los elementos que no tengan soft-delete (campo borrado). Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/producto'
     * @return
     */
    @GetMapping
    public List<Producto> todos(){
        return service.all();
    }

    /**
     * Devuelve todos los registros, incluidos los que tengan soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/producto/all'
     * @return
     */
    @GetMapping(value="/all")
    public List<Producto> todosAdmin(){
        return service.allWithDeleted();
    }

    /**
     * Busca un objeto por su numero de id, no incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/producto/show/99'
     * @param id
     * @return
     */
    @GetMapping(value="/show/{id}")
    public ResponseEntity<ProductoResponseDTO> mostrar(@PathVariable Long id){
        Optional<Producto> rta = service.showById(id);
        if(rta.isPresent())
        {
            Producto pojo=rta.get();
            return new ResponseEntity<ProductoResponseDTO>(buildResponse(pojo), HttpStatus.OK);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Busca un objeto por su numero de id, incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/producto/showWithDeleted/16'
     * @param id
     * @return
     */
    @GetMapping(value="/showWithDeleted/{id}")
    public Optional<Producto> mostrarAdmin(@PathVariable Long id){
        Optional<Producto> obj=service.showByIdWithDeleted(id);

        return obj;
    }

    /**
     * Persiste un objeto. Método POST
     * ej:
     * curl --location --request POST 'localhost:9088/api/producto' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "marca": "Tramontina",
     *     "modelo": "Serrucho",
     *     "precio": 132.50
     * }
     * '
     * @param form
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<Object> guardar(@Valid @RequestBody ProductoForm form, BindingResult result) throws Exception{
        if(result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
        Producto producto = form.toPojo();
        service.insert(producto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(producto.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

        return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
    }

    /**
     * Actualiza una entidad. Método PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/producto/101' \
     * --header 'Content-Type: application/json' \
     * --data-raw ' {
     *      "id":"101",
     *     "modelo": "Serrucho",
     *     "marca": "Tramontina Plus",
     *     "precio": 250.5
     * }'
     * @param form
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object>  actualizar(@RequestBody ProductoForm form, @PathVariable long id)
    {
        Optional<Producto> rta = service.showById(id);
        if(!rta.isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra el Producto que desea modificar.");

        else{
            Producto p = form.toPojo();
            service.update(p);
            return ResponseEntity.ok(buildResponse(p));
        }
    }

    /**
     * Realiza soft-delete sobre una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/producto/102'
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id)
    {
        if(!service.showById(id).isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe un Producto con ese id");
        service.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * Restaura una entidad que fue marcada con soft-delete. Metodo PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/producto/recycle/102'
     * @param id
     * @return
     */
    @PutMapping(value="/recycle/{id}")
    public Producto reciclar(@PathVariable Long id){
        Optional<Producto> obj=service.showByIdWithDeleted(id);
        service.recycle(obj.get());

        return obj.get();
    };

    /**
     * Realiza la destruccion de una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/producto/destroy/102'
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
    private ProductoResponseDTO buildResponse(Producto pojo) {
        ProductoResponseDTO dto = new ProductoResponseDTO(pojo);
        Link selfLink = WebMvcLinkBuilder.linkTo(ProductoRestController.class)
                .slash(pojo.getId())
                .withSelfRel();
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
