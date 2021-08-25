package com.invillia.reinventchallenge.shoppingcart.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundUser(UserNotFoundException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setError_key(ex.ERROR_KEY);
        errorResponse.setResource(ex.RESOURCE);
        errorResponse.setResource_key(ex.getResourceKey());



        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ShoppingCartNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundShoppingCart(ShoppingCartNotFoundException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setError_key(ex.ERROR_KEY);
        errorResponse.setResource(ex.RESOURCE);
        errorResponse.setResource_key(ex.getResourceKey());



        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundProduct(ProductNotFoundException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setError_key(ex.ERROR_KEY);
        errorResponse.setResource(ex.RESOURCE);
        errorResponse.setResource_key(ex.getResourceKey());



        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
