package com.invillia.reinvent.invilliareinventchallenge.repository;

import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String>{
}
