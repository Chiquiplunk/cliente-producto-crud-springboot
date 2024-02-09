package edu.tienda.core.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteRenderController {

    @GetMapping(value = "/clientes-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String getClienteAsHtml(){

        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append(" <cliente>");
        sb.append("    <li>Nombre: David Lima</li>");
        sb.append("    <li>UserName: DL</li>");
        sb.append(" </cliente>");
        sb.append("</xml>");
        return sb.toString();
    }
}

