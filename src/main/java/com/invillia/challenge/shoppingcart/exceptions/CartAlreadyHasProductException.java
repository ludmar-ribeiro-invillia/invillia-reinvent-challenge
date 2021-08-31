package com.invillia.challenge.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CartAlreadyHasProductException extends RuntimeException {
    protected final String RESOURCE= "Cart";
    protected final String ERROR_KEY= "cart.already.have.product";
    private final String resourceKey;

    public CartAlreadyHasProductException(String message, String resourceKey) {
        super(message);
        this.resourceKey = resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}
