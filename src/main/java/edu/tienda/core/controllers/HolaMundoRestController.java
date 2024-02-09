package edu.tienda.core.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoRestController {

    //@PathVariable que indica que este parámetro java normal se transformará en un parámetro web
    //de tipo Path Variable, es decir, un parámetro de tipo web que estará contenido en la URL del
    //endpoint.
    @GetMapping("/hola/{nombreUsuario}")
    public String saludo(@PathVariable String nombreUsuario) {
        System.out.println("Ejecutando el método saludo");
        return "Hola " + nombreUsuario;
    }
}
