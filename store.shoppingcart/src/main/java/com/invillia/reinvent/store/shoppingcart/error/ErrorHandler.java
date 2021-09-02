package com.invillia.reinvent.store.shoppingcart.error;

import com.invillia.reinvent.store.shoppingcart.exception.ProductItemNotFoundException;
import com.invillia.reinvent.store.shoppingcart.exception.ShoppingCartNotFoundException;
import com.invillia.reinvent.store.shoppingcart.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    /*@ExceptionHandler
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ErrorResponse HttpStatus404Handler() {
        throw new HttpStatus404Exception(
                "There's no Shopping Cart for the given user id"
        );
    }*/

    @ExceptionHandler(ProductItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> productItemNotFoundHandler(ProductItemNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.of(
                        "product-item",
                        "product-item.not.found",
                        exception.getMessage(),
                        exception.getResourceKey()
                )
        );
    }

    @ExceptionHandler(ShoppingCartNotFoundException.class)
    public ResponseEntity<ErrorResponse> shoppingCartNotFoundHandler(ShoppingCartNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.of(
                        "shopping-cart",
                        "shopping-cart.not.found",
                        exception.getMessage(),
                        "0"
                )
        );
    }
}