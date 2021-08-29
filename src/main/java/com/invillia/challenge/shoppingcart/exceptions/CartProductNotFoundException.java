package com.invillia.challenge.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CartProductNotFoundException extends RuntimeException{
    protected final String RESOURCE= "Cart";
    protected final String ERROR_KEY= "cart.product.sku.not.found";
    private final String resourceKey;

    public CartProductNotFoundException(String message, String resourceKey) {
        super(message);
        this.resourceKey = resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}
