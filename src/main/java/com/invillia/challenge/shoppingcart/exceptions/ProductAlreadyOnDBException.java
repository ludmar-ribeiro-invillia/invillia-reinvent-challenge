package com.invillia.challenge.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductAlreadyOnDBException extends RuntimeException {
    protected final String RESOURCE= "product";
    protected final String ERROR_KEY= "product.already.on.DB";
    private final String resourceKey;

    public ProductAlreadyOnDBException(String message, String resourceKey) {
        super(message);
        this.resourceKey = resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}