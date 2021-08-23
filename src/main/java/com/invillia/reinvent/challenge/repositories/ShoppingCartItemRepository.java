package com.invillia.reinvent.challenge.repositories;

import com.invillia.reinvent.challenge.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
}