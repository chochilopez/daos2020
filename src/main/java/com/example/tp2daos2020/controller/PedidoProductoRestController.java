package com.example.tp2daos2020.controller;

import com.example.tp2daos2020.controller.error.MensajeError;
import com.example.tp2daos2020.dto.PedidoProductoResponseDTO;
import com.example.tp2daos2020.entities.Pedido;
import com.example.tp2daos2020.entities.PedidoProducto;
import com.example.tp2daos2020.entities.Producto;
import com.example.tp2daos2020.service.PedidoProductoServiceImpl;
import com.example.tp2daos2020.service.PedidoServiceImpl;
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
@RequestMapping(value="/api/pedidoProducto")
public class PedidoProductoRestController {

    @Autowired
    private PedidoProductoServiceImpl service;

    @Autowired
    private PedidoServiceImpl pedidoService;

    @Autowired
    private ProductoServiceImpl productoService;

    /**
     * Permite filtrar por pedido. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedidoProducto/searchPedido?id=71'
     * @param id
     * @return
     */
    @GetMapping(value="/searchPedido")
    public List<PedidoProductoResponseDTO> filtrarPedidos(@RequestParam(name = "id") Long id) {
        List<PedidoProducto> pedidoProductos = service.showByPedido(id);
        List<PedidoProductoResponseDTO> objects=new ArrayList<PedidoProductoResponseDTO>();
        for (PedidoProducto pojo : pedidoProductos)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Permite filtrar por pedido. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedidoProducto/searchProducto?id=22'
     * @param id
     * @return
     */
    @GetMapping(value="/searchProducto")
    public List<PedidoProductoResponseDTO> filtrarProductos(@RequestParam(name = "id") Long id) {
        List<PedidoProducto> pedidoProductos = service.showByProducto(id);
        List<PedidoProductoResponseDTO> objects=new ArrayList<PedidoProductoResponseDTO>();
        for (PedidoProducto pojo : pedidoProductos)
            objects.add(buildResponse(pojo));

        return objects;
    }

    /**
     * Devuelve todos los elementos que no tengan soft-delete (campo borrado). Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedidoProducto'
     * @return
     */
    @GetMapping
    public List<PedidoProducto> todos(){
        return service.all();
    }

    /**
     * Devuelve todos los registros, incluidos los que tengan soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedidoProducto/all'
     * @return
     */
    @GetMapping(value="/all")
    public List<PedidoProducto> todosAdmin(){
        return service.allWithDeleted();
    }

    /**
     * Busca un objeto por su numero de id, no incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedidoProducto/show/55'
     * @param id
     * @return
     */
    @GetMapping(value="/show/{id}")
    public ResponseEntity<PedidoProductoResponseDTO> mostrar(@PathVariable Long id){
        Optional<PedidoProducto> rta = service.showById(id);
        if(rta.isPresent())
        {
            PedidoProducto pojo=rta.get();
            return new ResponseEntity<PedidoProductoResponseDTO>(buildResponse(pojo), HttpStatus.OK);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /**
     * Busca un objeto por su numero de id, incluye aquellos con soft-delete. Método GET
     * ej:
     * curl --location --request GET 'localhost:9088/api/pedidoProducto/showWithDeleted/99'
     * @param id
     * @return
     */
    @GetMapping(value="/showWithDeleted/{id}")
    public Optional<PedidoProducto> mostrarAdmin(@PathVariable Long id){
        Optional<PedidoProducto> obj=service.showByIdWithDeleted(id);

        return obj;
    }

    /**
     * Persiste un objeto. Método POST
     * ej:
     * curl --location --request POST 'localhost:9088/api/pedidoProducto' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "id": 96,
     *     "total": 123,
     *     "cantidad":200,
     *     "pedido": {
     *         "id": 46
     *     },
     *     "producto": {
     *         "id":89
     *     }
     * }'
     * @param form
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping
    public ResponseEntity<Object> guardar(@Valid @RequestBody PedidoProductoForm form, BindingResult result) throws Exception{
        if(result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatearError(result));
        PedidoProducto pedidoProducto = form.toPojo();
        Optional<Pedido> pedido = pedidoService.showById(form.getPedido().getId());
        if(pedido.isPresent())
            pedidoProducto.setPedido(pedido.get());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Pedido Requerido", "El pedido indicado no se encuentra en la base de datos."));
        Optional<Producto> producto = productoService.showById(form.getProducto().getId());
        if(producto.isPresent())
            pedidoProducto.setProducto(producto.get());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getError("02", "Producto Requerido", "El producto indicado no se encuentra en la base de datos."));
        productoService.insert(producto.get());
        pedidoService.insert(pedido.get());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pedidoProducto.getId()).toUri(); //Por convención en REST, se devuelve la  url del recurso recién creado

        return ResponseEntity.created(location).build();//201 (Recurso creado correctamente)
    }

    /**
     * Actualiza una entidad. Método PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/pedidoProducto/96' \
     * --header 'Content-Type: application/json' \
     * --data-raw '{
     *     "id": 96,
     *     "total": 222,
     *     "cantidad":1,
     *     "pedido": {
     *         "id": 46
     *     },
     *     "producto": {
     *         "id":89
     *     }
     * }'
     * @param form
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object>  actualizar(@RequestBody PedidoProductoForm form, @PathVariable long id)
    {
        Optional<PedidoProducto> rta = service.showById(id);
        if(!rta.isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encuentra el renglon que desea modificar.");
        else {
            PedidoProducto p = form.toPojo();
            Optional<Pedido> c = pedidoService.showById(form.getPedido().getId());
            if(c.isPresent())
                p.setPedido(c.get());
            else
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El pedido indicado no se encuentra en la base de datos.");
            Optional<Producto> pro = productoService.showById(form.getProducto().getId());
            if(pro.isPresent())
                p.setProducto(pro.get());
            else
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El producto indicado no se encuentra en la base de datos.");

            p.setId(id);  //El id es el identificador, con lo cual es el único dato que no permito modificar
            service.update(p);
            return ResponseEntity.ok(buildResponse(p));
        }
    }

    /**
     * Realiza soft-delete sobre una entidad. Método DELETE
     * ej:
     * curl --location --request DELETE 'localhost:9088/api/pedidoProducto/96'
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id)
    {
        if(!service.showById(id).isPresent())
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No existe un renglon con ese id");
        service.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * Restaura una entidad que fue marcada con soft-delete. Metodo PUT
     * ej:
     * curl --location --request PUT 'localhost:9088/api/pedidoProducto/recycle/96'
     * @param id
     * @return
     */
    @PutMapping(value="/recycle/{id}")
    public PedidoProducto reciclar(@PathVariable Long id){
        Optional<PedidoProducto> obj=service.showByIdWithDeleted(id);
        service.recycle(obj.get());

        return obj.get();
    };

    /**
     * Realiza la destruccion de una entidad. Método DELETE
     * Tener en cuenta que no permite borrado por constraints
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
    private PedidoProductoResponseDTO buildResponse(PedidoProducto pojo) {
        PedidoProductoResponseDTO dto = new PedidoProductoResponseDTO(pojo);
        Link selfLink = WebMvcLinkBuilder.linkTo(CiudadRestController.class)
                .slash(pojo.getId())
                .withSelfRel();
        Link pedidoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PedidoRestController.class)
                .mostrar(pojo.getPedido().getId()))
                .withRel("pedido");
        Link productoLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductoRestController.class)
                .mostrar(pojo.getProducto().getId()))
                .withRel("producto");
        dto.add(selfLink);
        dto.add(pedidoLink);
        dto.add(productoLink);

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
