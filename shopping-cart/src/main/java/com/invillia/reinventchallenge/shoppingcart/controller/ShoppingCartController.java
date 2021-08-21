package com.invillia.reinventchallenge.shoppingcart.controller;

import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoRequest;
import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoResponse;
import com.invillia.reinventchallenge.shoppingcart.dto.ShoppingCartDto;
import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import com.invillia.reinventchallenge.shoppingcart.service.ShoppingCartService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
                .map(shoppingCart -> mapperShoppingCartToShoppingCartDto(shoppingCart))
                .collect(Collectors.toList());
        log.info("listDto={}",listDto);


        if (shoppingCartAll.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/{user-id}/items/{sku}")
    public ResponseEntity<ProductDtoResponse> addItemShoppingCart(
            @PathVariable("user-id") Long idUser,
            @PathVariable("sku") String sku,
            @RequestBody ProductDtoRequest productDto
    ) throws NotFoundException {
        log.info("idUser={}, sku={}, productDto={}", idUser, sku, productDto);

        final Product product = shoppingCartService.addProduct(idUser, sku, productDto);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapperProductToProductResponse(product));
    }


    private ProductDtoResponse mapperProductToProductResponse(Product product){
         ProductDtoResponse productDto = modelMapper.map(product, ProductDtoResponse.class);
         return productDto;
    }

    private Product mapperProductDtoToProduct(ProductDtoResponse productDto){
        Product product = modelMapper.map(productDto, Product.class);
        return product;

    }

    private ShoppingCartDto mapperShoppingCartToShoppingCartDto(ShoppingCart shoppingCart){
        ShoppingCartDto shoppingCartDto = modelMapper.map(shoppingCart, ShoppingCartDto.class);
        return shoppingCartDto;
    }

    private ShoppingCart mapperShoppingCartDtoToShoppingCart(ShoppingCartDto shoppingCartDto){
        ShoppingCart shoppingCart = modelMapper.map(shoppingCartDto, ShoppingCart.class);
        return shoppingCart;
    }

}
