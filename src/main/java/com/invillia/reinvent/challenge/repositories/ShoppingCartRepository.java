package com.invillia.reinvent.challenge.repositories;

import com.invillia.reinvent.challenge.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
