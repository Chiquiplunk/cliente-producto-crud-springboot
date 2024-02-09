package edu.tienda.core.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tienda.core.domain.Producto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Primary cómo lo dice la palabra distingue a un bean de otro indicando que el
 * que está marcado con esta anotación debe tener prioridad para la inyección por sobre otros
 * servicios “hermanos”. Es decir, servicios que implementan una misma interfaz.
 */
@Primary
/**
 * @Service registra clases en el contenedor de
 * dependencias Spring que luego generará un solo objeto de la misma durante toda la sesión.
 */
@Service("JSON")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_JSON"
)
public class ProductosServiceJSONImpl implements ProductoService{

    //Este código situado en la imagen superior es el que carga el archivo JSON de productos
    //desde el classpath del proyecto y lo deserializa a una lista de productos.
    public List<Producto> getProductos(){
        List<Producto> productos;
        try {
            productos = new ObjectMapper()
                    .readValue(this.getClass().getResourceAsStream("/productos.json"),
                            new TypeReference<List<Producto>>() {});

            return productos;

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveProducto(Producto producto){

    }

}

