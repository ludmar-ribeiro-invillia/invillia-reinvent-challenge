package com.invillia.reinvent.challenge.repositories;

import com.invillia.reinvent.challenge.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
