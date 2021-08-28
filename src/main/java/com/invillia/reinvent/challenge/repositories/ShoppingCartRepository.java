package com.invillia.reinvent.challenge.repositories;

import com.invillia.reinvent.challenge.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserId(Long userId);




}
