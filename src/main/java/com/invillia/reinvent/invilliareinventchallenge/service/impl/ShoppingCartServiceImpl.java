package com.invillia.reinvent.invilliareinventchallenge.service.impl;

import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import com.invillia.reinvent.invilliareinventchallenge.entity.ShoppingCart;
import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import com.invillia.reinvent.invilliareinventchallenge.repository.ShoppingCartRepository;
import com.invillia.reinvent.invilliareinventchallenge.repository.UserRepository;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public Optional<ShoppingCart> findByUser(Long userId) {
        Optional<ShoppingCart> u = this.shoppingCartRepository.findByUser(userId);
        return u;
    }

    public ShoppingCart save(ShoppingCart cart) {
        return this.shoppingCartRepository.save(cart);
    }

    public List<ShoppingCart> findAllByUser(Long userId){
        return this.shoppingCartRepository.findAllByUser(userId);
    }
    public void deleteById(Long shopCartId){
         this.shoppingCartRepository.deleteById(shopCartId);
    }
   /*public Optional<Item> findAllByProductId(Long userId, Long productId) {

        return this.shoppingCartRepository.findAllByProductId(userId,productId);
    }*/


}
