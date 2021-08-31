package com.invillia.reinvent.challenge.resources;

import com.invillia.reinvent.challenge.entities.ShoppingCartItem;
import com.invillia.reinvent.challenge.request.AddShoppingCartItemRequest;
import com.invillia.reinvent.challenge.response.ListShoppingCartItemResponse;
import com.invillia.reinvent.challenge.response.ShoppingCartItemResponse;
import com.invillia.reinvent.challenge.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartResource {

    @Autowired
    private ShoppingCartService service;

    // OK
    @PostMapping("{userId}/items/{sku}")
    public ResponseEntity<ShoppingCartItemResponse> addItem(@PathVariable Long userId, @PathVariable Long sku,
                                                            @RequestBody AddShoppingCartItemRequest request){

        ShoppingCartItem shoppingCartItem = service.addItem(userId, sku, request);

        ShoppingCartItemResponse shoppingCartItemResponse= new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);
        shoppingCartItemResponse.setName(shoppingCartItem.getProduct().getName());
        shoppingCartItemResponse.setPrice(shoppingCartItem.getProduct().getPrice());
        shoppingCartItemResponse.setQuantity(request.getQuantity());

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.CREATED);
    }

    // OK
    @DeleteMapping("{userId}/items/{sku}")
    public ResponseEntity<ShoppingCartItemResponse> removeItem(@PathVariable Long userId, @PathVariable Long sku) {

        ShoppingCartItem shoppingCartItem =  service.removeItem(userId, sku);

        ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);
        shoppingCartItemResponse.setName(shoppingCartItem.getProduct().getName());
        shoppingCartItemResponse.setPrice(shoppingCartItem.getProduct().getPrice());
        shoppingCartItemResponse.setQuantity(shoppingCartItem.getQuantity());

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);
    }

    // OK
    @DeleteMapping("{userId}")
    public ResponseEntity<ListShoppingCartItemResponse> removeAll(@PathVariable Long userId) {

        List<ShoppingCartItem> shoppingCartItemList = service.removeAll(userId);

        ListShoppingCartItemResponse listShoppingCartItemResponse = new ListShoppingCartItemResponse();

        Double total = 0.0;

        for(ShoppingCartItem item : shoppingCartItemList){
            ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();
            shoppingCartItemResponse.setSku(item.getProduct().getSku());
            shoppingCartItemResponse.setName(item.getProduct().getName());
            shoppingCartItemResponse.setPrice(item.getProduct().getPrice());
            shoppingCartItemResponse.setQuantity(item.getQuantity());

            listShoppingCartItemResponse.getItems().add(shoppingCartItemResponse);

            total += shoppingCartItemResponse.getPrice() * shoppingCartItemResponse.getQuantity();
        }

        listShoppingCartItemResponse.setTotal(total);

        return new ResponseEntity<>(listShoppingCartItemResponse, HttpStatus.OK);

    }

    //OK
    @GetMapping("{userId}")
    public ResponseEntity<ListShoppingCartItemResponse> listShoppingCart(@PathVariable Long userId){

        List<ShoppingCartItem> shoppingCartItemList = service.listShoppingCart(userId);

        ListShoppingCartItemResponse listShoppingCartItemResponse = new ListShoppingCartItemResponse();

        Double total = 0.0;

        for(ShoppingCartItem item : shoppingCartItemList){
            ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();
            shoppingCartItemResponse.setSku(item.getProduct().getSku());
            shoppingCartItemResponse.setName(item.getProduct().getName());
            shoppingCartItemResponse.setPrice(item.getProduct().getPrice());
            shoppingCartItemResponse.setQuantity(item.getQuantity());

            listShoppingCartItemResponse.getItems().add(shoppingCartItemResponse);

            total += shoppingCartItemResponse.getPrice() * shoppingCartItemResponse.getQuantity();
        }

        listShoppingCartItemResponse.setTotal(total);

        return new ResponseEntity<>(listShoppingCartItemResponse, HttpStatus.OK);
    }

    //OK
    @GetMapping("{userId}/items/{sku}")
    public ResponseEntity<ShoppingCartItemResponse> getItem(@PathVariable Long userId, @PathVariable Long sku){

        ShoppingCartItem shoppingCartItem =  service.getItem(userId, sku);

        ShoppingCartItemResponse shoppingCartItemResponse = new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);
        shoppingCartItemResponse.setName(shoppingCartItem.getProduct().getName());
        shoppingCartItemResponse.setPrice(shoppingCartItem.getProduct().getPrice());
        shoppingCartItemResponse.setQuantity(shoppingCartItem.getQuantity());

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);
    }

    @PatchMapping("{userId}/items/{sku}")
    public ResponseEntity<ShoppingCartItemResponse> editQuantity(@PathVariable Long userId, @PathVariable Long sku,
                                                                 @RequestParam Integer quantity){

        ShoppingCartItem shoppingCartItem =  service.editQuantity(userId, sku, quantity);

        ShoppingCartItemResponse shoppingCartItemResponse= new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);
        shoppingCartItemResponse.setName(shoppingCartItem.getProduct().getName());
        shoppingCartItemResponse.setPrice(shoppingCartItem.getProduct().getPrice());
        shoppingCartItemResponse.setQuantity(shoppingCartItem.getQuantity());

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);
    }

    //OK
    @PutMapping("{userId}/items/{sku}")
    ResponseEntity<ShoppingCartItemResponse> editItem(@PathVariable Long userId, @PathVariable Long sku,
                                                      @RequestBody AddShoppingCartItemRequest request) {

        ShoppingCartItem shoppingCartItem = service.editItem(userId, sku, request);

        ShoppingCartItemResponse shoppingCartItemResponse= new ShoppingCartItemResponse();
        shoppingCartItemResponse.setSku(sku);
        shoppingCartItemResponse.setName(shoppingCartItem.getProduct().getName());
        shoppingCartItemResponse.setPrice(shoppingCartItem.getProduct().getPrice());
        shoppingCartItemResponse.setQuantity(shoppingCartItem.getQuantity());

        return new ResponseEntity<>(shoppingCartItemResponse, HttpStatus.OK);
    }

 }

