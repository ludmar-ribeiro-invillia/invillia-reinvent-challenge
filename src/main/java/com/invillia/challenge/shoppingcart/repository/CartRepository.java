package com.invillia.challenge.shoppingcart.repository;

import com.invillia.challenge.shoppingcart.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    Cart findByProduct_SkuAndCustomer_Id(String sku,Integer id);
    List<Cart> findByCustomer_Id(Integer id);
    @Transactional
    void deleteByCustomer_Id(Integer id);
}
