package com.invillia.reinventchallenge.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionUser extends RuntimeException{
    public ExceptionUser(String message) {
        super(message);
    }
}
