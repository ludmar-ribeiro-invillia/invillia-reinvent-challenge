package com.invillia.reinvent.store.shoppingcart.controller;

import com.invillia.reinvent.store.shoppingcart.model.ProductItem;
import com.invillia.reinvent.store.shoppingcart.model.ShoppingCart;
import com.invillia.reinvent.store.shoppingcart.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-cart/{userId}")
public class ShoppingCartController {

    private ShoppingCartService service;

    public ShoppingCartController(
            ShoppingCartService service
    ) {
        this.service = service;
    }

    @PostMapping("/items/{sku}")
    public ResponseEntity<ProductItem> addProductItem(
            @PathVariable Integer userId,
            @PathVariable Integer sku,
            @RequestBody ProductItem productItem
    ) {
        productItem.setUserId(userId);
        productItem.setSku(sku);
        ProductItem result = service.addProductItem(productItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @PutMapping("/items/{sku}")
    public ResponseEntity<ProductItem> setProductItem(
            @PathVariable Integer userId,
            @PathVariable Integer sku,
            @RequestBody ProductItem productItem
    ) {
        productItem.setUserId(userId);
        productItem.setSku(sku);
        ProductItem result = service.setProductItem(productItem);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping("/items/{sku}")
    public ProductItem getProductItem(
            @PathVariable Integer userId,
            @PathVariable Integer sku
    ) {
        return service.getProductItem(userId, sku);
    }

    @GetMapping
    public ShoppingCart getShoppingCart(
            @PathVariable Integer userId
    ) {
        return service.getShoppingCart(userId);
    }

    @PatchMapping("/items/{sku}")
    public ProductItem setProductItemQuantity(
            @PathVariable Integer userId,
            @PathVariable Integer sku,
            @RequestParam Integer quantity
    ) {
        return service.setProductItemQuantity(
                userId,
                sku,
                quantity
        );
    }

    @DeleteMapping(path = "")
    public ShoppingCart delShoppingCart (
            @PathVariable Integer userId
    ) {
        return service.deleteShoppingCart(userId);
    }

    @DeleteMapping(path = "/items/{sku}")
    public ResponseEntity<ProductItem> delSku (
            @PathVariable("userId") Integer userId,
            @PathVariable("sku") Integer sku
    ) {

        ProductItem result = service.deleteProductItem(userId, sku);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }
}