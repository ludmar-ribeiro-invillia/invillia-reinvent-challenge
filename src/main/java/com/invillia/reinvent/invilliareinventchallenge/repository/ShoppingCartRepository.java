package com.invillia.reinvent.invilliareinventchallenge.repository;

import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import com.invillia.reinvent.invilliareinventchallenge.entity.ShoppingCart;
import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query(value = "select * from shopping_cart sc " +
                   " where sc.user_id = :userId ", nativeQuery = true)
    Optional<ShoppingCart> findByUser(Long userId);

    @Query(value = "select * from shopping_cart sc " +
            " where sc.user_id = :userId ", nativeQuery = true)
    List<ShoppingCart> findAllByUser(Long userId);


    void deleteById(Long shopCartId);

}
