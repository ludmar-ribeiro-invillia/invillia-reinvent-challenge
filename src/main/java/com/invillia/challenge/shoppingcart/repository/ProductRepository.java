package com.invillia.challenge.shoppingcart.repository;

import com.invillia.challenge.shoppingcart.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    Product findBySku(String sku);
}
