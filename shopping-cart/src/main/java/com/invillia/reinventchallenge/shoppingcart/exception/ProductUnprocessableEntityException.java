package com.invillia.reinventchallenge.shoppingcart.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ProductUnprocessableEntityException extends RuntimeException{

    protected final String RESOURCE= "product";
    protected final String ERROR_KEY= "product.unprocessable.entity";
    @Getter
    private String resourceKey;

    public ProductUnprocessableEntityException(String message, String sku) {
        super(message);
        this.resourceKey = sku;
    }
}
