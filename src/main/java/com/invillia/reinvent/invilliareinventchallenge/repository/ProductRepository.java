package com.invillia.reinvent.invilliareinventchallenge.repository;


import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
