package com.invillia.reinvent.challenge.resources;

import com.invillia.reinvent.challenge.entities.Product;
import com.invillia.reinvent.challenge.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;


    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value ="/{sku}")
    public ResponseEntity<Product> findById(@PathVariable Long sku){
            Product product = service.findById(sku);
            return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/sku}").buildAndExpand(obj.getSku()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value ="/{sku}")
    public ResponseEntity<Void> delete(@PathVariable Long sku){
        service.delete(sku);
        return ResponseEntity.noContent().build();
    }

}
