package edu.infnet.rest_tp3.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.infnet.rest_tp3.DAL.ItemRepository;
import edu.infnet.rest_tp3.DAL.ProdutoRepository;
import edu.infnet.rest_tp3.models.Fornecedor;
import edu.infnet.rest_tp3.models.Item;
import edu.infnet.rest_tp3.models.Produto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

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
@RequestMapping("/api/itens")
public class ItemController {

    @Autowired
    private ItemRepository ItemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.ItemRepository = itemRepository;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return ItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ItemRepository.findById(id)
                .map(Item -> ResponseEntity.ok().body(Item))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Item createItem(@RequestBody Item Item) {
        return ItemRepository.save(Item);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        return ItemRepository.findById(id)
                .map(Item -> {
                    
                    Item.setCodigo(updatedItem.getCodigo());
                    Item.setFabricacao(updatedItem.getFabricacao());
                    Item.setValidade(updatedItem.getValidade());
                    Item.setValidade(updatedItem.getValidade());
                    Item.setTipo(updatedItem.getTipo());

                    ItemRepository.save(Item);
                    return ResponseEntity.ok(Item);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        return ItemRepository.findById(id)
                .map(Item -> {
                    ItemRepository.delete(Item);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
