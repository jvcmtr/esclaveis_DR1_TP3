package edu.infnet.rest_tp3.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.infnet.rest_tp3.DAL.ProdutoRepository;
import edu.infnet.rest_tp3.models.Produto;

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
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository ProdutoRepository;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return ProdutoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        return ProdutoRepository.findById(id)
                .map(Produto -> ResponseEntity.ok().body(Produto))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Produto createProduto(@RequestBody Produto Produto) {
        return ProdutoRepository.save(Produto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto updatedProduto) {
        return ProdutoRepository.findById(id)
                .map(Produto -> {
                    
                    Produto.setNome(updatedProduto.getNome());
                    Produto.setPrecoCompra(updatedProduto.getPrecoCompra());
                    Produto.setPrecoVenda(updatedProduto.getPrecoVenda());
                    Produto.setRefrigerado(updatedProduto.getRefrigerado());
                    Produto.setSetor(updatedProduto.getSetor());
 
                    ProdutoRepository.save(Produto);
                    return ResponseEntity.ok(Produto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        return ProdutoRepository.findById(id)
                .map(Produto -> {
                    ProdutoRepository.delete(Produto);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
