package edu.tienda.core.services;

import edu.tienda.core.domain.Producto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Service: Esta anotación hará que la clase quede registrada por Spring en su contenedor de
 * dependencias. De esta manera será Spring el encargado de producir instancias de esta clase
 * automáticamente. Este tipo patrón se llama “inversión del control" y también se lo conoce como
 * patrón “Hollywood”
 */
@Lazy

/**
 * @Slf4j
 * La ventaja de utilizar la anotación @Slf4j es que cargará el sistema de trazas definido por
 * default en la solución. Que para nuestro caso es Logback. Pero si el día de mañana quisiéramos
 * importar dependencias de Lo4j2 y definirlo como el principal para nuestro proyecto. No
 * deberíamos cambiar una sola línea de código ya que Slf4j cargará el nuevo sistema de trazas
 * definido para generar los logs necesarios.
 */
@Slf4j
@Service("MEMORY")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_MEMORIA")
public class ProductosServiceImpl implements ProductoService{

    private List<Producto> productos= new ArrayList<>(Arrays.asList(
            new Producto(1, "Smart TV", 9000.0,3),
            new Producto(2, "Pc Notebook", 15000.0,10),
            new Producto(3, "Tablet", 8000.0,5)
    ));

    public ProductosServiceImpl(){

        log.info("Se está construyendo un objeto de las clases ProductosServiceImpl");
    }

    public List<Producto> getProductos(){

        return productos;
    }

    @Override
    public void saveProducto(Producto producto){

    }

}

