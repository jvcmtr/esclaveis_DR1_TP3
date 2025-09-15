package edu.infnet.rest_tp3.controllers;

import java.time.LocalDateTime;
import java.util.List;

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
import edu.infnet.rest_tp3.DAL.CompraRepository;
import edu.infnet.rest_tp3.models.Compra;

@RestController
@RequestMapping("/api/compras")
public class CompraController {
     private final CompraRepository compraRepository;

    public CompraController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Compra> createCompra(@RequestBody Compra compra) {
        compra.setDataVenda(LocalDateTime.now());
        Compra savedCompra = compraRepository.save(compra);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompra);
    }

    // READ ALL
    @GetMapping
    public List<Compra> getAllCompras() {
        return compraRepository.findAll();
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Compra> getCompraById(@PathVariable Long id) {
        return compraRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompra(@PathVariable Long id, @RequestBody Compra compraDetails) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não se pode alterar uma compra após sua criação.");
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompra(@PathVariable Long id) {
        return compraRepository
            .findById(id)
            .map(compra -> {
                compraRepository.delete(compra);
                return ResponseEntity.noContent().build();
            })
            .orElse(
                ResponseEntity.notFound().build()
            );
    }
}
