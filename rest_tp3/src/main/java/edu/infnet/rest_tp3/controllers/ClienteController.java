package edu.infnet.rest_tp3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.infnet.rest_tp3.DAL.ClienteRepository;
import edu.infnet.rest_tp3.models.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository ClienteRepository;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return ClienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return ClienteRepository.findById(id)
                .map(Cliente -> ResponseEntity.ok().body(Cliente))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente Cliente) {
        return ClienteRepository.save(Cliente);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente updatedCliente) {
        return ClienteRepository.findById(id)
                .map(Cliente -> {
                    
                    Cliente.setNome(updatedCliente.getNome());
                    Cliente.setDocumento(updatedCliente.getDocumento());
                    Cliente.setEndereco(updatedCliente.getEndereco());

                    ClienteRepository.save(Cliente);
                    return ResponseEntity.ok(Cliente);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        return ClienteRepository.findById(id)
                .map(Cliente -> {
                    ClienteRepository.delete(Cliente);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
