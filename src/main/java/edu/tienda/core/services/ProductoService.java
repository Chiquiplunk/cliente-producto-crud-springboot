package edu.tienda.core.services;

import edu.tienda.core.domain.Cliente;
import edu.tienda.core.domain.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public interface ProductoService {

    public List<Producto> getProductos();

    public void saveProducto(Producto producto);


}
