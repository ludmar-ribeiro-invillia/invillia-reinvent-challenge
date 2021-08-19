package com.invillia.reinventchallenge.shoppingcart.controller;

import com.invillia.reinventchallenge.shoppingcart.dto.ProductDto;
import com.invillia.reinventchallenge.shoppingcart.dto.ShoppingCartDto;
import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import com.invillia.reinventchallenge.shoppingcart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ShoppingCartDto>> getShoppingCartList(){
        log.info("getShoppingCartList");
        final List<ShoppingCart> shoppingCartAll = shoppingCartService.getShoppingCartAll();
        log.info("getShoppingCartList, shoppingCartAll={}",shoppingCartAll);
        final List<ShoppingCartDto> listDto = shoppingCartAll.stream()
                .map(shoppingCart -> convertShoppingCartToDto(shoppingCart))
                .collect(Collectors.toList());
        log.info("listDto={}",listDto);


        if (shoppingCartAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(listDto);
    }









    private ProductDto convertToDto (Product product){
         ProductDto productDto = modelMapper.map(product, ProductDto.class);
         return productDto;
    }

    private Product convertToEntity(ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        return product;

    }

    private ShoppingCartDto convertShoppingCartToDto (ShoppingCart shoppingCart){
        ShoppingCartDto shoppingCartDto = modelMapper.map(shoppingCart, ShoppingCartDto.class);
        return shoppingCartDto;
    }

    private ShoppingCart convertShoppingCartToEntity(ShoppingCartDto shoppingCartDto){
        ShoppingCart shoppingCart = modelMapper.map(shoppingCartDto, ShoppingCart.class);
        return shoppingCart;
    }

}
