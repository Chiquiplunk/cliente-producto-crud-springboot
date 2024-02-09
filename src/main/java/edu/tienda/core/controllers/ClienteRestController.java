package edu.tienda.core.controllers;

import edu.tienda.core.domain.Cliente;
import edu.tienda.core.exceptions.BadRequestException;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

//crearemos un almacén de clientes
//en memoria. Esto lo implementaremos con una simple lista que simulara ser una
//especie de base de datos de clientes.
@RestController
@RequestMapping("clientes")
public class ClienteRestController {
    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("arm", "1234","Armstrong"),
            new Cliente("ald", "1234","Aldrins"),
            new Cliente("col", "1234","Collins")
        )
    );

    //Obtención de todos los clientes del sistema
    @GetMapping
    public ResponseEntity<?> getClientes(){

        return ResponseEntity.ok(clientes);
    }

    //método de recupero de cliente
    @GetMapping("/{userName}")
    public ResponseEntity<?> getCliente(@PathVariable String userName){

        if (userName.length()!=3){
            throw new BadRequestException("El parámetro nombre de usuario debe contener 3 caracteres");
        }

        return clientes.stream()
                .filter(cliente -> cliente.getUsername().equalsIgnoreCase(userName))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente " + userName + " no encontrado"));
    }

    //método java con el nombre “altaCliente” que reciba
    //un cliente y devuelva también una clase del mismo tipo
    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente){
        clientes.add(cliente);

        //Obteniendo URL de servicio
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/userName")
                .buildAndExpand(cliente.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(cliente);
    }

    @PutMapping
    public ResponseEntity<?> modificacionCliente(@RequestBody Cliente cliente){

        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername())).
                findFirst().orElseThrow();

        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());

        return ResponseEntity.ok(clienteEncontrado);
    }

    @DeleteMapping(" /{userName}")
    public ResponseEntity deleteCliente(@PathVariable String userName){

        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();

        clientes.remove(clienteEncontrado);

        return ResponseEntity.noContent().build();
    }

}

