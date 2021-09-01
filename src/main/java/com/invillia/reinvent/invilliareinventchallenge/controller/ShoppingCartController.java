package com.invillia.reinvent.invilliareinventchallenge.controller;

import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import com.invillia.reinvent.invilliareinventchallenge.repository.ItemRepository;
import com.invillia.reinvent.invilliareinventchallenge.repository.ProductRepository;
import com.invillia.reinvent.invilliareinventchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequestMapping("/shopping-cart")
@RestController
public class ShoppingCartController {

    @Autowired
    private ItemRepository itemRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;


    //Recuperar o carrinho de compras
    @GetMapping(value = "/{id}")
    public List<Item> listItems(@PathVariable String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Iterable<Item> itemIterable = itemRepository.findAll();
            return StreamSupport.stream(itemIterable.spliterator(), false).collect(Collectors.toList());
        }
        return null;
    }


    //Recuperar um produto do carrinho de compras
    @GetMapping (value = "/{id}/items/{sku}")
    public ResponseEntity<Item> getItemById(@PathVariable String sku, @PathVariable String id) {
        Optional<User> idUser = userRepository.findById(id);
        Optional<Item> itemOptional = itemRepository.findById(sku);
        if (itemOptional.isPresent() && idUser.isPresent()) {
            return ResponseEntity.ok().body(itemOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    //Adicionar itens
    @PostMapping(value = "/{id}/items/{sku}")
    public ResponseEntity<Item> createShoppingCart(@PathVariable String sku, @PathVariable String id, @RequestBody Item item) {
        Optional<User> idUser = userRepository.findById(id);
        Optional<Product> productOptional = productRepository.findById(sku);
        if (productOptional.isPresent() && idUser.isPresent()) {
            itemRepository.save(item);
           return ResponseEntity.ok().body(item);
       }
        return ResponseEntity.notFound().build();
    }

    //Editar quantidade de itens através do corpo da requisição
    @PutMapping (value = "/{id}/items/{sku}")
    public ResponseEntity<Item> updateItemById(@PathVariable String sku, @PathVariable String id, @RequestBody Item item) {
        Optional<Item> itemOptional = itemRepository.findById(sku);
        Optional<User> idUser = userRepository.findById(id);
        if (itemOptional.isPresent() && idUser.isPresent()) {
            itemRepository.save(item);
            return ResponseEntity.ok().body(item);
        }
        return ResponseEntity.notFound().build();
    }


    //Editar quantidade de itens através do query params
    @PatchMapping (value = "/{id}/items/{sku}")
    public ResponseEntity<Item> updateQtd (@PathVariable String id, @PathVariable String sku, @RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {
        Optional<Item> itemOptional = itemRepository.findById(sku);
        Optional<User> idUser = userRepository.findById(id);
        if (itemOptional.isPresent() && idUser.isPresent()) {
            itemRepository.save(itemOptional.get());
            return ResponseEntity.ok().body(itemOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    // Remover um produto do carrinho
    @DeleteMapping (value = "/{id}/items/{sku}")
    public ResponseEntity<Item> deleteItemById(@PathVariable String sku, @PathVariable String id ) {
        Optional<User> idUser = userRepository.findById(id);
        Optional<Item> itemOptional = itemRepository.findById(sku);
        if (itemOptional.isPresent() && itemOptional.isPresent()) {
            itemRepository.deleteById(sku);
            return ResponseEntity.ok().body(itemOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        Optional<User> idUser = userRepository.findById(id);
        if (idUser.isPresent()) {
                itemRepository.deleteAll();
                return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
