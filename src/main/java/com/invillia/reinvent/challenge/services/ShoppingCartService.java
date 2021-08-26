package com.invillia.reinvent.challenge.services;

import com.invillia.reinvent.challenge.entities.ShoppingCart;
import com.invillia.reinvent.challenge.repositories.ShoppingCartRepository;
import com.invillia.reinvent.challenge.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository repository;

    public ShoppingCart findById(Long id){
        Optional<ShoppingCart> shoppingCart = repository.findById(id);
        ShoppingCart cart;
        if (shoppingCart.equals(Optional.empty())) {
            cart = new ShoppingCart(id);
        }
        else {
            cart = shoppingCart.orElseThrow(() -> new ResourceNotFoundException(id));
        }
        return repository.save(cart);
    }

    public ShoppingCart addItem(Long id){
        return null;
    }






}
