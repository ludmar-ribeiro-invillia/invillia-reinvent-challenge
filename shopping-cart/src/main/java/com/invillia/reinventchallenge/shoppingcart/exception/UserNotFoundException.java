package com.invillia.reinventchallenge.shoppingcart.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    protected final String RESOURCE= "user";
    protected final String ERROR_KEY= "user.not.found";
    @Getter
    private String resourceKey;

    public UserNotFoundException(String message, String resourceKey) {
        super(message);
        this.resourceKey = resourceKey;
    }
}
