package com.invillia.challenge.shoppingcart.controller;

import com.invillia.challenge.shoppingcart.dto.ItemDto;
import com.invillia.challenge.shoppingcart.dto.ItemResponseDto;
import com.invillia.challenge.shoppingcart.entity.Product;
import com.invillia.challenge.shoppingcart.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/shopping-cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/{user-id}/items/{SKU}")
    public ResponseEntity<ItemResponseDto> addItem (@PathVariable("user-id") Integer userId, @PathVariable("SKU") String sku , @RequestBody ItemDto item) {
        final Product product = shoppingCartService.AddItem(userId, sku, item);
        return ResponseEntity.ok().body(convertProductToItemResponseDto(product));
    }

//    @PutMapping(path = "/{user-id}/items/{SKU}")
//    public ResponseEntity<ItemResponseDto> editItem (@PathVariable("user-id") String userId,@PathVariable("SKU") String sku, @RequestBody ItemDto item) {
//
//    }
//
//    @PatchMapping(path = "/{user-id}/items/{SKU}?quantity={quantity}")
//    public ResponseEntity<ItemResponseDto> editQuantity (@PathVariable("user-id") String userId,@PathVariable("SKU") String sku, @PathVariable("quantity")) {
//
//    }
//
//    @DeleteMapping(path = "{user-id}/items/{SKU}")
//    public ResponseEntity<ItemResponseDto> deleteItem (@PathVariable("user-id") String userId,@PathVariable("SKU") String sku) {
//
//    }
//
//    @GetMapping(path = "{user-id}")
//    public ResponseEntity<CartDto> getCart (@PathVariable("user-id") String userId) {
//
//    }

    private ItemResponseDto convertProductToItemResponseDto(Product product) {
        ItemResponseDto itemResponseDto = modelMapper.map(product, ItemResponseDto.class);
        return itemResponseDto;
    }

}
