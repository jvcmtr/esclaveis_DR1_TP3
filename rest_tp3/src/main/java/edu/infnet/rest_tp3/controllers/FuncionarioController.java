package edu.infnet.rest_tp3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.infnet.rest_tp3.DAL.FuncionarioRepository;
import edu.infnet.rest_tp3.models.Funcionario;

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
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository FuncionarioRepository;

    @GetMapping
    public List<Funcionario> getAllFuncionarios() {
        return FuncionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
        return FuncionarioRepository.findById(id)
                .map(Funcionario -> ResponseEntity.ok().body(Funcionario))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Funcionario createFuncionario(@RequestBody Funcionario Funcionario) {
        return FuncionarioRepository.save(Funcionario);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario updatedFuncionario) {
        return FuncionarioRepository.findById(id)
                .map(Funcionario -> {
                    
                    Funcionario.setNome(updatedFuncionario.getNome());
                    Funcionario.setSalario(updatedFuncionario.getSalario());
                    Funcionario.setCargo(updatedFuncionario.getCargo());
                    Funcionario.setCTPS(updatedFuncionario.getCTPS());
                    Funcionario.setDocumento(updatedFuncionario.getDocumento());

                    FuncionarioRepository.save(Funcionario);
                    return ResponseEntity.ok(Funcionario);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Long id) {
        return FuncionarioRepository.findById(id)
                .map(Funcionario -> {
                    FuncionarioRepository.delete(Funcionario);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
