package com.invillia.challenge.shoppingcart.controller;

import com.invillia.challenge.shoppingcart.dto.CartDto;
import com.invillia.challenge.shoppingcart.dto.ItemDto;
import com.invillia.challenge.shoppingcart.dto.ItemResponseDto;
import com.invillia.challenge.shoppingcart.dto.ProductDto;
import com.invillia.challenge.shoppingcart.entity.Cart;
import com.invillia.challenge.shoppingcart.entity.Product;
import com.invillia.challenge.shoppingcart.service.ProductService;
import com.invillia.challenge.shoppingcart.service.ShoppingCartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/{SKU}")
    public ResponseEntity<ItemResponseDto> addItem (@PathVariable("SKU") String sku , @RequestBody ProductDto product) {
        final ItemResponseDto itemResponse = productService.AddProduct(sku, product);
        return ResponseEntity.ok().body(itemResponse);
    }
}
