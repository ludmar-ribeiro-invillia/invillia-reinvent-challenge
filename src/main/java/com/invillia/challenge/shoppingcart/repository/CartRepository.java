package com.invillia.challenge.shoppingcart.repository;

import com.invillia.challenge.shoppingcart.entity.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Integer> {
    Cart findByProduct_SkuAndCustomer_Id(String sku,Integer id);
}
