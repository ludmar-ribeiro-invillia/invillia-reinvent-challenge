package com.invillia.reinventchallenge.shoppingcart.repository;

import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ShoppingCartRepository extends ElasticsearchRepository<ShoppingCart, String> {
}
