package com.invillia.reinvent.store.shoppingcart.exception;

public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(String message) {
        super(message);
    }
}