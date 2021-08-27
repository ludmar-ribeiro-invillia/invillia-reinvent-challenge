package com.invillia.reinvent.challenge.repositories;

import com.invillia.reinvent.challenge.entities.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    void deleteById(Long sku);
}
