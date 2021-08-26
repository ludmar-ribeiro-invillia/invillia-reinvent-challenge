package com.invillia.reinvent.challenge.services;

import com.invillia.reinvent.challenge.entities.Product;
import com.invillia.reinvent.challenge.repositories.ProductRepository;
import com.invillia.reinvent.challenge.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long sku){
        Optional<Product> product = repository.findById(sku);
        return product.orElseThrow(() -> new ResourceNotFoundException(sku));
    }

    public Product insert(Product product){
        return repository.save(product);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
