package com.invillia.reinvent.challenge.resources;

import com.invillia.reinvent.challenge.request.AddShoppingCartItemRequest;
import com.invillia.reinvent.challenge.response.ShoppingCartItemResponse;
import com.invillia.reinvent.challenge.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartResource {

    @Autowired
    private ShoppingCartService service;

    @PostMapping("{userId}/items/{sku}")
    public ResponseEntity<ShoppingCartItemResponse> addItem(@PathVariable Long userId, @PathVariable Long sku,
                                                            @RequestBody AddShoppingCartItemRequest request){

        service.addItem(userId, sku, request);

        ShoppingCartItemResponse shoppingCartItemResponse= new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);
        shoppingCartItemResponse.setName(request.getName());
        shoppingCartItemResponse.setPrice(request.getPrice());
        shoppingCartItemResponse.setQuantity(request.getQuantity());

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.CREATED);

    }

    @DeleteMapping("{userId}/items/{sku}")
    public ResponseEntity<ShoppingCartItemResponse> removeItem(@PathVariable Long userId, @PathVariable Long sku) {

        //removi todos os itens de um produto
        service.removeItem(userId, sku);

        ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);
        shoppingCartItemResponse.getName();
        shoppingCartItemResponse.getPrice();
        shoppingCartItemResponse.getQuantity();


        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);

    }

 }

