package com.invillia.reinvent.shoppingcart.repository;

import com.invillia.reinvent.shoppingcart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, String>{
}
