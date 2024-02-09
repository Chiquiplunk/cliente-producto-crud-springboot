package edu.tienda.core.controllers;

import edu.tienda.core.configurations.ConfigurationParameters;
import edu.tienda.core.domain.Cliente;
import edu.tienda.core.domain.Producto;
import edu.tienda.core.services.ProductoService;
import edu.tienda.core.services.ProductosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosControllerRest {

    @GetMapping("/fake-productos")
    public ResponseEntity<?> fakeProductosAPI(){

        List<Producto> productos = new ArrayList<>(Arrays.asList(
                new Producto(1,"Camiseta Juventus",1200.0,4),
                new Producto(2,"Camiseta River plate",1000.0,8),
                new Producto(3,"Camiseta Boca Juniors",900.0,1)
        ));

        //Retornamos los productos del servicio en el body de la respuesta
        return ResponseEntity.ok(productos);
    }

    /**
     * @Autowired: La anotación @Autowired indica a Spring que tiene que buscar en su contenedor de
     * inyecciones una clase que implemente a la interfaz que decora. Luego Spring, creará una
     * instancia de “ProductosServiceImpl” y la guardará en la referencia “ProductoService” de
     * manera transparente tanto al programador como a la clase controladora.
     */
    //Se instancia la clase de servicio al estilo "Java puro"
    @Autowired
    @Lazy
    private ProductoService productosService;

    @Autowired
    private ConfigurationParameters ConfigurationParameters;

    @GetMapping
    public ResponseEntity<?> getProductos(){

        System.out.println("params: " + ConfigurationParameters.toString());

        //Se recuperan todos los productos del servicio
        List<Producto> productos = productosService.getProductos();

        //Retornamos los productos del servicio en el body de la respuesta
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<?> altaProducto(@RequestBody Producto producto){
        productosService.saveProducto(producto);

        //Obteniendo URL de servicio
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(producto.getId())
                .toUri();

        return ResponseEntity.created(location).body(producto);
    }

}

