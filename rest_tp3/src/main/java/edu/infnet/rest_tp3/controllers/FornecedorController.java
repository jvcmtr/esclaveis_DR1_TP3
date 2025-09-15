package edu.infnet.rest_tp3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.infnet.rest_tp3.DAL.FornecedorRepository;
import edu.infnet.rest_tp3.models.Fornecedor;

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
@RequestMapping("/api/Fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository FornecedorRepository;

    @GetMapping
    public List<Fornecedor> getAllFornecedors() {
        return FornecedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable Long id) {
        return FornecedorRepository.findById(id)
                .map(Fornecedor -> ResponseEntity.ok().body(Fornecedor))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Fornecedor createFornecedor(@RequestBody Fornecedor Fornecedor) {
        return FornecedorRepository.save(Fornecedor);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> updateFornecedor(@PathVariable Long id, @RequestBody Fornecedor updatedFornecedor) {
        return FornecedorRepository.findById(id)
                .map(Fornecedor -> {
                    
                    Fornecedor.setNome(updatedFornecedor.getNome());
                    Fornecedor.setDocumento(updatedFornecedor.getDocumento());

                    FornecedorRepository.save(Fornecedor);
                    return ResponseEntity.ok(Fornecedor);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFornecedor(@PathVariable Long id) {
        return FornecedorRepository.findById(id)
                .map(Fornecedor -> {
                    FornecedorRepository.delete(Fornecedor);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
