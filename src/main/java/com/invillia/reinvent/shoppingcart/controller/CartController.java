package com.invillia.reinvent.shoppingcart.controller;

import com.invillia.reinvent.shoppingcart.entity.Cart;
import com.invillia.reinvent.shoppingcart.entity.Product;
import com.invillia.reinvent.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/shopping-cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    private Cart cart;

    @RequestMapping(value = "/{id}/items/{sku}", method = RequestMethod.POST)
    public Cart addProductCart(@PathVariable("id") int id,
                               @PathVariable("sku") String sku,
                               @RequestBody Product product) {
        product.setSku(sku);

        if (cartRepository.findByIdUser(id) == null) {
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            Cart cart = new Cart(id, productList);
            return cartRepository.save(cart);
        } else {
            Cart cart = cartRepository.findByIdUser(id);
            cart.addProduct(product);
            return cartRepository.save(cart);
        }
    }

    @RequestMapping(value = "/{id}/items/{sku}", method = RequestMethod.PUT)
    public Cart updateProductCart(@PathVariable("id") int id,
                                  @PathVariable("sku") String sku,
                                  @RequestBody Product product) {
        Cart cart = cartRepository.findByIdUser(id);
        for (Product prod : cart.getProduct()) {
            if (prod.getSku().contentEquals(sku)) {
                prod.setPrice(product.getPrice());
                prod.setName(product.getName());
                prod.setQuantity(product.getQuantity());
            }
        }
        return cartRepository.save(cart);
    }

    //@RequestMapping(value = "/{id}/items/{sku}?quantity={newQuantity}", method = RequestMethod.PATCH,
    //consumes = "application/json-patch+json")
    @PatchMapping(path = "/{id}/items/{sku}?quantity={newQuantity}")
    public Cart updateProductQuantityCart(@PathVariable("id") int id,
                                          @PathVariable("sku") String sku,
                                          @PathVariable("newQuantity") String newQuantity) {
        Cart cart = cartRepository.findByIdUser(id);
        for (Product prod : cart.getProduct()) {
            if (prod.getSku().contentEquals(sku)) {
                prod.setQuantity(Integer.parseInt(newQuantity));
            }
        }
        return cartRepository.save(cart);
    }

    @RequestMapping(value = "/{id}/items/{sku}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductCart(@PathVariable("id") int id,
                                               @PathVariable("sku") String sku) {
        Cart cart = cartRepository.findByIdUser(id);
        for (Product prod : cart.getProduct()) {
            if (prod.getSku().contentEquals(sku)) {
                cartRepository.deleteByIdProduct(prod.getId(), cart.getId());
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCart(@PathVariable("id") int id) {
        Cart cart = cartRepository.findByIdUser(id);
        cartRepository.deleteById(cart.getId());
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Cart> getCart() {
        return cartRepository.findAll();
    }

    @RequestMapping(value = "/{id}/items/{sku}", method = RequestMethod.GET)
    public Product getProductCart(@PathVariable("id") int id,
                                 @PathVariable("sku") String sku) {
        Cart cart = cartRepository.findByIdUser(id);
        for (Product prod : cart.getProduct()) {
            if (prod.getSku().contentEquals(sku)) {
                   return prod;
            }
        }
        return null;
    }
}
