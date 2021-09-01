package com.invillia.reinvent.invilliareinventchallenge.repository;

import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import com.invillia.reinvent.invilliareinventchallenge.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * from item i " +
            "inner join shopping_cart sc on sc.id = i.cart_id" +
            " where i.product_id = :productId and sc.user_id = :userId", nativeQuery = true)
    Optional<Item> findAllByProductId(Long userId, Long productId);


    @Query(value = "select * from item i " +
            "inner join shopping_cart sc on sc.id = i.cart_id" +
            " where sc.user_id = :userId", nativeQuery = true)
    List<Item> findAllByUser(Long userId);

    void deleteById(Long id);


}
