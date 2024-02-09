package edu.tienda.core.services;

import edu.tienda.core.domain.Cliente;
import edu.tienda.core.domain.Producto;
import edu.tienda.core.persistance.entities.ProductoEntity;
import edu.tienda.core.persistance.repositories.ProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("BD")
@ConditionalOnProperty(
        value = "productos.estrategia",
        havingValue = "EN_BD")
public class ProductosServiceBDImpl  implements ProductoService {

    @Autowired
    private ProductosRepository productosRepository;

    public List<Producto> getProductos() {
        List<Producto> productos = productosRepository.findAll().
                stream().map(productoEntity -> {
                    Producto producto = new Producto();
                    producto.setId(productoEntity.getId());
                    producto.setNombre(productoEntity.getNombre());
                    producto.setPrecio(productoEntity.getPrecio());
                    producto.setStock(productoEntity.getStock());
                    return producto;
                }).collect(Collectors.toList());

        return productos;
    }
    @Override
    public void saveProducto(Producto producto) {
        //Mapeo de producto a ProductoEntity
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setNombre(producto.getNombre());
        productoEntity.setPrecio(producto.getPrecio());
        productoEntity.setStock(producto.getStock());

        //Persistencia
        productosRepository.save(productoEntity);
    }

}

