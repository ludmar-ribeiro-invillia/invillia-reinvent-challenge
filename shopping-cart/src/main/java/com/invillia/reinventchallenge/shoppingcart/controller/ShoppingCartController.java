package com.invillia.reinventchallenge.shoppingcart.controller;

import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoRequest;
import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoResponse;
import com.invillia.reinventchallenge.shoppingcart.dto.ShoppingCartDto;
import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import com.invillia.reinventchallenge.shoppingcart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
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
    public ResponseEntity<List<ShoppingCartDto>> getShoppingCartList() {
        log.info("getShoppingCartList");
        final List<ShoppingCart> shoppingCartAll = shoppingCartService.getAll();
        log.info("getShoppingCartList, shoppingCartAll={}", shoppingCartAll);

        if (shoppingCartAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        final List<ShoppingCartDto> listDto = shoppingCartAll.stream()
                .map(shoppingCart -> mapperShoppingCartToShoppingCartDto(shoppingCart))
                .collect(Collectors.toList());
        log.info("listDto={}", listDto);
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping(value = "/{user-id}/items/{sku}")
    public ResponseEntity<ProductDtoResponse> addItemShoppingCart(
            @PathVariable("user-id") Long idUser,
            @PathVariable("sku") String sku,
            @Valid @RequestBody ProductDtoRequest productDto) {
        log.info("idUser={}, sku={}, productDto={}", idUser, sku, productDto);
        final Product product = shoppingCartService.addProduct(idUser, sku, productDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapperProductToProductResponse(product));
    }

    @PutMapping(value = "/{user-id}/items/{sku}")
    public ResponseEntity<ProductDtoResponse> putItemShoppingCart(
            @PathVariable("user-id") Long idUser,
            @PathVariable("sku") String sku,
            @RequestBody ProductDtoRequest productDto) {
        log.info("putItemShoppingCart, idUser={}, sku={}, productDto={}", idUser, sku, productDto);
        final Product product = shoppingCartService.putProduct(idUser, sku, productDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapperProductToProductResponse(product));
    }

    @PatchMapping(value = "/{user-id}/items/{sku}")
    public ResponseEntity<ProductDtoResponse> editNumberOfItem(
            @PathVariable("user-id") Long idUser,
            @PathVariable("sku") String sku,
            @RequestParam("quantity") Integer quantity) {
        log.info("editNumberOfItem, idUser={}, sku={}, newQuantity={}", idUser, sku, quantity);
        final Product product = shoppingCartService.editNumberOfItem(idUser, sku, quantity);
        return ResponseEntity.ok().body(mapperProductToProductResponse(product));
    }

    @DeleteMapping(value = "/{user-id}/items/{sku}")
    public ResponseEntity<ProductDtoResponse> removeProductShoppingCart(
            @PathVariable("user-id") Long idUser,
            @PathVariable("sku") String sku) {
        log.info("removeProductShoppingCart, idUser={}, sku={}", idUser, sku);
        final Product product = shoppingCartService.removeProductShoppingCart(idUser, sku);
        return ResponseEntity.ok().body(mapperProductToProductResponse(product));
    }

    @GetMapping(value = "/{user-id}/items/{sku}")
    public ResponseEntity<ProductDtoResponse> retrieveProductShoppingCart(
            @PathVariable("user-id") Long idUser,
            @PathVariable("sku") String sku) {
        log.info("retrieveProductShoppingCart, idUser={}, sku={}", idUser, sku);
        final Product product = shoppingCartService.retrieveProductShoppingCart(idUser, sku);
        return ResponseEntity.ok().body(mapperProductToProductResponse(product));
    }

    @GetMapping(value = "/{user-id}")
    public ResponseEntity<ShoppingCartDto> retrieveShoppingCart(@PathVariable("user-id") Long idUser) {
        log.info("retrieveShoppingCart, idUser={}", idUser);
        final ShoppingCart shoppingCart = shoppingCartService.retrieveShoppingCart(idUser);
        return ResponseEntity.ok().body(mapperShoppingCartToShoppingCartDto(shoppingCart));
    }

    @DeleteMapping(value = "/{user-id}")
    public ResponseEntity<ShoppingCartDto> removeShoppingCart(@PathVariable("user-id") Long idUser) {
        log.info("removeShoppingCart, idUser={}", idUser);
        final ShoppingCart shoppingCart = shoppingCartService.removeShoppingCart(idUser);
        return ResponseEntity.ok().body(mapperShoppingCartToShoppingCartDto(shoppingCart));
    }

    private ProductDtoResponse mapperProductToProductResponse(Product product) {
        return modelMapper.map(product, ProductDtoResponse.class);
    }

    private Product mapperProductDtoToProduct(ProductDtoResponse productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    private ShoppingCartDto mapperShoppingCartToShoppingCartDto(ShoppingCart shoppingCart) {
        ShoppingCartDto shoppingCartDto = modelMapper.map(shoppingCart, ShoppingCartDto.class);
        BigDecimal sum = BigDecimal.ZERO;
        for (Product product : shoppingCart.getListProducts()) {
            sum = sum.add(product.calculateSumProduct());
        }
        shoppingCartDto.setTotal(sum);
        return shoppingCartDto;
    }

    private ShoppingCart mapperShoppingCartDtoToShoppingCart(ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCart = modelMapper.map(shoppingCartDto, ShoppingCart.class);
        return modelMapper.map(shoppingCartDto, ShoppingCart.class);
    }
}
