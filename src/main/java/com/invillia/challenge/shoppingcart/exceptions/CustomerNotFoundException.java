package com.invillia.challenge.shoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
    protected final String RESOURCE= "customer";
    protected final String ERROR_KEY= "customer.not.found";
    private final String resourceKey;

    public CustomerNotFoundException(String message, String resourceKey) {
        super(message);
        this.resourceKey = resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}
