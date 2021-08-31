package com.invillia.reinventchallenge.shoppingcart.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException {

    protected final String RESOURCE = "shoppingCart";
    protected final String ERROR_KEY = "shoppingCart.not.found";
    @Getter
    private String resourceKey;

    public ShoppingCartNotFoundException(String message, String resourceKey) {
        super(message);
        this.resourceKey = resourceKey;
    }
}
