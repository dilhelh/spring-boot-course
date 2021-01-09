package io.github.dilhelh.rest.controller;

import io.github.dilhelh.domain.entity.Cliente;
import io.github.dilhelh.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id){
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}