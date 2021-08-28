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

        service.removeItem(userId, sku);

        ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);

    }

    @GetMapping("{userId}")
    public ResponseEntity<ShoppingCartItemResponse> findById(@PathVariable Long userId){

         service.findById(userId);

        ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<ShoppingCartItemResponse> removeShoppingCart(@PathVariable Long userId) {

        service.removeShoppingCart(userId);

        ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);

    }


 }

