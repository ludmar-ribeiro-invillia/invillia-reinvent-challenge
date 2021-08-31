package com.invillia.reinvent.shoppingcart.controller;

import com.invillia.reinvent.shoppingcart.entity.Cart;
import com.invillia.reinvent.shoppingcart.entity.Product;
import com.invillia.reinvent.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Product> addProductCart(@PathVariable("id") int id,
                               @PathVariable("sku") String sku,
                               @RequestBody Product product) {
        product.setSku(sku);
        try{
            if (cartRepository.findByIdUser(id) == null) {
                List<Product> productList = new ArrayList<>();
                productList.add(product);
                Cart cart = new Cart(id, productList);
                cartRepository.save(cart);
            } else {
                Cart cart = cartRepository.findByIdUser(id);
                cart.addProduct(product);
                cartRepository.save(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.accepted().body(product);
    }

    @RequestMapping(value = "/{id}/items/{sku}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProductCart(@PathVariable("id") int id,
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
        cartRepository.save(cart);
        product.setSku(sku);
        return ResponseEntity.accepted().body(product);
    }

    @PatchMapping(path = "/{id}/items/{sku}")
    public ResponseEntity<?> updateProductQuantityCart(@PathVariable("id") int id,
                                          @PathVariable("sku") String sku,
                                          @RequestParam("quantity") String newQuantity) {
        Cart cart = cartRepository.findByIdUser(id);
        for (Product prod : cart.getProduct()) {
            if (prod.getSku().contentEquals(sku)) {
                prod.setQuantity(Integer.parseInt(newQuantity));
                return ResponseEntity.ok().body(cart);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}/items/{sku}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProductCart(@PathVariable("id") int id,
                                               @PathVariable("sku") String sku) {
        Cart cart = cartRepository.findByIdUser(id);
        for (Product prod : cart.getProduct()) {
            if (prod.getSku().contentEquals(sku)) {
                cartRepository.deleteByIdCartProduct(prod.getId(), cart.getId());
                cartRepository.deleteByIdProduct(prod.getId());
                return ResponseEntity.ok().body(prod);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCart(@PathVariable("id") int id) {
        Cart cart = cartRepository.findByIdUser(id);
        cartRepository.deleteById(cart.getId());
        return ResponseEntity.ok().body(cart);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Cart> getCart() {
        return cartRepository.findAll();
    }

    @RequestMapping(value = "/{id}/items/{sku}", method = RequestMethod.GET)
    public ResponseEntity<?> getProductCart(@PathVariable("id") int id,
                                 @PathVariable("sku") String sku) {
        Cart cart = cartRepository.findByIdUser(id);
        for (Product prod : cart.getProduct()) {
            if (prod.getSku().contentEquals(sku)) {
                return ResponseEntity.ok().body(prod);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
