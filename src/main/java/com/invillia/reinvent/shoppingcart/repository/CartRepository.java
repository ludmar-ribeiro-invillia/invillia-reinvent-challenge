package com.invillia.reinvent.shoppingcart.repository;

import com.invillia.reinvent.shoppingcart.entity.Cart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.beans.Transient;

public interface CartRepository extends CrudRepository<Cart, Integer> {

    @Query(value = "SELECT * FROM cart WHERE id_user = ?1", nativeQuery = true)
    Cart findByIdUser(Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM cart_product WHERE product_id = ?1 AND cart_id=?2", nativeQuery = true)
    void deleteByIdCartProduct(Integer idProduct, Integer idCart);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM product WHERE id = ?1", nativeQuery = true)
    void deleteByIdProduct(Integer idProduct);
}
