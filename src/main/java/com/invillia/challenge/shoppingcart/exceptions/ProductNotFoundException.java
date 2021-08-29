package com.invillia.challenge.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    protected final String RESOURCE= "product";
    protected final String ERROR_KEY= "product.not.found";
    private final String resourceKey;

    public ProductNotFoundException(String message, String resourceKey) {
        super(message);
        this.resourceKey = resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}
