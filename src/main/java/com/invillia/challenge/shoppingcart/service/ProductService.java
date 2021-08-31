package com.invillia.challenge.shoppingcart.service;

import com.invillia.challenge.shoppingcart.dto.ItemResponseDto;
import com.invillia.challenge.shoppingcart.dto.ProductDto;
import com.invillia.challenge.shoppingcart.entity.Product;
import com.invillia.challenge.shoppingcart.exceptions.ProductAlreadyOnDBException;
import com.invillia.challenge.shoppingcart.exceptions.ProductNotFoundException;
import com.invillia.challenge.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ItemResponseDto AddProduct(String sku, ProductDto product) {
        validateProduct(sku);
        productRepository.save(new Product(product.getPrice(), product.getName(), sku));
        return new ItemResponseDto(sku, product.getName(), product.getPrice(),1 );
    }

    public void validateProduct(String sku) {
        Product product = productRepository.findBySku(sku);
        if(product == null) {
            throw new ProductAlreadyOnDBException("Product sku already on database.", sku);
        }
    }
}
