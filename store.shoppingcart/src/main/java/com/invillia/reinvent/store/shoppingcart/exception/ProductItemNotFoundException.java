package com.invillia.reinvent.store.shoppingcart.exception;

public class ProductItemNotFoundException extends RuntimeException{

    private String resourceKey;

    public ProductItemNotFoundException(String message, String resourceKey) {
        super(message);

        this.resourceKey= resourceKey;
    }

    public String getResourceKey() {
        return resourceKey;
    }
}