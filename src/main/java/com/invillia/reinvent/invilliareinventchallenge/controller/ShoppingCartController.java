package com.invillia.reinvent.invilliareinventchallenge.controller;


import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import com.invillia.reinvent.invilliareinventchallenge.entity.ShoppingCart;
import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingCartRecord;
import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingRecordTotal;
import com.invillia.reinvent.invilliareinventchallenge.repository.ItemRepository;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.ProductService;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.ShoppingCartService;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/{userId}/items/{productId}")
    public ShoppingCartRecord create(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, @RequestBody Item itemUser) {


        Optional<User> usu = Optional.of(userService.findById(userId).get());
        Optional<Product> prod = Optional.of(productService.findById(productId).get());
        Optional<ShoppingCart> shopCart = this.shoppingCartService.findByUser(usu.get().getId());
        Item item = new Item();
        Optional<Item> i = this.itemRepository.findAllByProductId(usu.get().getId(), prod.get().getId());
        if (!shopCart.isPresent()) {
            shopCart = Optional.of(new ShoppingCart());
        }
        if (i.isPresent()) {
            i.get().setQuantity(i.get().getQuantity() + 1);
            this.itemRepository.save(i.get());
            return ShoppingCartMapShoppingCartRecord(shopCart.get(), i.get());
        } else {

            if (itemUser.getQuantity() == null) {
                item.setQuantity(1);
            }
            item.setQuantity(itemUser.getQuantity());
            item.setProduct(prod.get());
            shopCart.get().setUser(usu.get());
            item.setCart(shoppingCartService.save(shopCart.get()));
            this.itemRepository.save(item);
            return ShoppingCartMapShoppingCartRecord(shopCart.get(), item);
        }

    }

    @PutMapping("/{userId}/items/{productId}")
    public ShoppingCartRecord editByProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, @RequestBody Item itemUser) {

        Optional<User> usu = Optional.of(userService.findById(userId).get());
        Optional<Product> prod = Optional.of(productService.findById(productId).get());

        Optional<ShoppingCart> cart = shoppingCartService.findByUser(usu.get().getId());

        Optional<Item> i = this.itemRepository.findAllByProductId(usu.get().getId(), prod.get().getId());

        i.get().setQuantity(itemUser.getQuantity());

        if (itemUser.getQuantity() == null) {
            i.get().setQuantity(1);
        } else {
            i.get().setQuantity(itemUser.getQuantity());
        }
        i.get().setProduct(prod.get());
        this.itemRepository.save(i.get());

        return ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
    }

    @PatchMapping("/{userId}/items/{productId}")
    public ShoppingCartRecord editByQuantity(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, @RequestParam("quantity") String qtd) {

        Optional<User> usu = Optional.of(userService.findById(userId).get());
        Optional<Product> prod = Optional.of(productService.findById(productId).get());

        Optional<ShoppingCart> cart = shoppingCartService.findByUser(usu.get().getId());

        Optional<Item> i = this.itemRepository.findAllByProductId(usu.get().getId(), prod.get().getId());

        i.get().setQuantity(Integer.parseInt(qtd));
        this.itemRepository.save(i.get());
        return ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public ShoppingCartRecord deleteByProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Optional<Item> i = this.itemRepository.findAllByProductId(Long.parseLong(String.valueOf(userId)), Long.parseLong(String.valueOf(productId)));
        Optional<ShoppingCart> cart = shoppingCartService.findByUser(Long.parseLong(String.valueOf(userId)));
        ShoppingCartRecord scr = ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
        this.itemRepository.delete(i.get());
        return scr;
    }

    @GetMapping("/{userId}/items/{productId}")
    public ShoppingCartRecord findById(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        Optional<Item> i = this.itemRepository.findAllByProductId(Long.parseLong(String.valueOf(userId)), Long.parseLong(String.valueOf(productId)));
        Optional<ShoppingCart> cart = shoppingCartService.findByUser(Long.parseLong(String.valueOf(userId)));

        return ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
    }

    @GetMapping("/{userId}")
    public ShoppingRecordTotal findByShoppingCartByUser(@PathVariable("userId") Long userId) {
        List<Item> itens = this.itemRepository.findAllByUser(Long.parseLong(String.valueOf(userId)));
        Optional<ShoppingCart> cart = shoppingCartService.findByUser(Long.parseLong(String.valueOf(userId)));
        if (cart.get().getItens() == null) {
            cart.get().setItens(new ArrayList<>());
        }
        BigDecimal tot = new BigDecimal(0);
        cart.get().setItens(itens);
        for (int i = 0; i < cart.get().getItens().size(); i++) {
            tot = tot.add(cart.get().getItens().get(i).getProduct().getPrice());
        }
        cart.get().setTotal(tot);
        return ShoppingCartMapShoppingRecordTotal(cart.get());
    }

    @DeleteMapping("/{userId}")
    public ShoppingRecordTotal deleteByShoppingCart(@PathVariable("userId") Long userId) {

        List<Item> itens = this.itemRepository.findAllByUser(Long.parseLong(String.valueOf(userId)));
        Optional<ShoppingCart> cart = shoppingCartService.findByUser(Long.parseLong(String.valueOf(userId)));
        if (cart.get().getItens() == null) {
            cart.get().setItens(new ArrayList<>());
        }
        BigDecimal tot = new BigDecimal(0);
        cart.get().setItens(itens);
        for (int i = 0; i < cart.get().getItens().size(); i++) {
            tot = tot.add(cart.get().getItens().get(i).getProduct().getPrice());
        }
        cart.get().setTotal(tot);
        ShoppingRecordTotal sct = ShoppingCartMapShoppingRecordTotal(cart.get());
        shoppingCartService.deleteById(cart.get().getId());
        for(int i = 0; i < itens.size(); i++){
            this.itemRepository.deleteById(itens.get(i).getId());
        }
        return sct;

    }

    public ShoppingCartRecord ShoppingCartMapShoppingCartRecord(ShoppingCart sc, Item item) {
        return new ShoppingCartRecord(sc.getId(),
                item.getProduct().getPrice(),
                item.getProduct().getDescription(),
                item.getQuantity());
    }

    public ShoppingRecordTotal ShoppingCartMapShoppingRecordTotal(ShoppingCart sc) {
        return new ShoppingRecordTotal(sc.getId(),
                sc.getItens(),
                sc.getTotal());
    }
}
