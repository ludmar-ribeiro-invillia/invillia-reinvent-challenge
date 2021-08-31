package com.invillia.reinventchallenge.shoppingcart.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{

    protected final String RESOURCE= "product";
    protected final String ERROR_KEY= "product.not.found";
    @Getter
    private String resourceKey;

    public ProductNotFoundException(String message, String sku) {
        super(message);
        this.resourceKey = sku;
    }
}
