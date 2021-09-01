package com.invillia.reinvent.invilliareinventchallenge.service.interfaces;

import com.invillia.reinvent.invilliareinventchallenge.entity.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCart> findByUser(Long userId);

    ShoppingCart save(ShoppingCart shoppingCart);

    List<ShoppingCart> findAllByUser(Long userId);

    void deleteById(Long userId);

    //Optional<Item> findAllByProductId(Long userId, Long productId);
}
