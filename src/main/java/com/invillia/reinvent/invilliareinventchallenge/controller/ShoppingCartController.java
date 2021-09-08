package com.invillia.reinvent.invilliareinventchallenge.controller;


import com.invillia.reinvent.invilliareinventchallenge.entity.Item;

import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingCartRecord;
import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingRecordTotal;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(path = "/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/{userId}/items/{productId}")
    public ResponseEntity<?> create(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, @RequestBody Item itemUser) {

        Optional<ShoppingCartRecord> scr = Optional.ofNullable(shoppingCartService.save(userId, productId, itemUser));
        if (scr.isPresent()) {
            return new ResponseEntity<ShoppingCartRecord>(scr.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Erro ao persistir o Shopping-cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/{userId}/items/{productId}")
    public ResponseEntity<?> editByProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, @RequestBody Item itemUser) {

        Optional<ShoppingCartRecord> scr = Optional.ofNullable(shoppingCartService.editProduct(userId, productId, itemUser));
        if (scr.isPresent()) {
            return new ResponseEntity<ShoppingCartRecord>(scr.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Erro ao editar o Shopping-cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/{userId}/items/{productId}")
    public ResponseEntity<?> editByQuantity(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, @RequestParam("quantity") String qtd) {
        Optional<ShoppingCartRecord> scr = Optional.ofNullable(shoppingCartService.editByQuantity(userId, productId, qtd));
        if (scr.isPresent()) {
            return new ResponseEntity<ShoppingCartRecord>(scr.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Erro ao editar o Shopping-cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<?>deleteByProduct(@PathVariable("userId") String userId, @PathVariable("productId") String productId) {
        Optional<ShoppingCartRecord> scr = Optional.ofNullable(shoppingCartService.deleteItem(userId, productId));
        if (scr.isPresent()) {
            return new ResponseEntity<ShoppingCartRecord>(scr.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Erro ao excluir o item do Shopping-cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}/items/{productId}")
    public ResponseEntity<?>  findProductByCartUser (@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {

        Optional<ShoppingCartRecord> srt = Optional.ofNullable(shoppingCartService.findProductByCartUser(userId,productId));
        if (srt.isPresent()) {
            return new ResponseEntity<ShoppingCartRecord>(srt.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<String>("Produto não localizado", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findByShoppingCartByUser(@PathVariable("userId") Long userId) {

        Optional<ShoppingRecordTotal> srt = Optional.ofNullable(shoppingCartService.findByShoppingCartByUser(userId));
        if (srt.isPresent()) {
            return new ResponseEntity<ShoppingRecordTotal>(srt.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<String>("Carrinho de compras não localizado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteByShoppingCart(@PathVariable("userId") Long userId) {
        Optional<ShoppingRecordTotal> srt = Optional.ofNullable(shoppingCartService.deleteShoppingCartByUserId(userId));
        if (srt.isPresent()) {
            return new ResponseEntity<ShoppingRecordTotal>(srt.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Não foi possível deletar ", HttpStatus.BAD_REQUEST);
        }

    }

}
