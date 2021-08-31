package com.invillia.reinventchallenge.shoppingcart.repository;

import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
