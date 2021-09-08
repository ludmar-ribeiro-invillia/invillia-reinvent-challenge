package com.invillia.reinvent.invilliareinventchallenge.controller;

import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import com.invillia.reinvent.invilliareinventchallenge.record.ProductRecord;
import com.invillia.reinvent.invilliareinventchallenge.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productCatalogService;

    @GetMapping
    public List<Product> listProducts() {
       return productCatalogService.findAll();
    }
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product prod) throws Exception{

        Product produto = this.productCatalogService.save(prod);
        if (produto.getId() != null) {
            return new ResponseEntity<ProductRecord>(this.mapProductRecord(produto), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<String>("Erro ao persistir o produto", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id)throws Exception {
        Optional<Product> prod = productCatalogService.findById(id);
        return prod.map(value -> ResponseEntity.ok(this.mapProductRecord(prod.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    private ProductRecord mapProductRecord(Product produto) {

        return new ProductRecord(produto.getDescription(),
                   produto.getPrice());
    }
    }



