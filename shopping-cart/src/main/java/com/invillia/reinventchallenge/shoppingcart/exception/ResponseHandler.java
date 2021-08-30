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
        final ErrorResponse errorResponse = constructErrorResponse(ex.getMessage(), ex.ERROR_KEY, ex.RESOURCE, ex.getResourceKey());

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ShoppingCartNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundShoppingCart(ShoppingCartNotFoundException ex, WebRequest request){
        final ErrorResponse errorResponse = constructErrorResponse(ex.getMessage(), ex.ERROR_KEY, ex.RESOURCE, ex.getResourceKey());

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundProduct(ProductNotFoundException ex, WebRequest request){
        final ErrorResponse errorResponse = constructErrorResponse(ex.getMessage(), ex.ERROR_KEY, ex.RESOURCE, ex.getResourceKey());

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ProductUnprocessableEntityException.class})
    protected ResponseEntity<Object> handleUnprocessableEntityProduct(ProductUnprocessableEntityException ex, WebRequest request){
        final ErrorResponse errorResponse = constructErrorResponse(ex.getMessage(), ex.ERROR_KEY, ex.RESOURCE, ex.getResourceKey());

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    private ErrorResponse constructErrorResponse(final String message, final String errorKey, final String resource, final String resourceKey){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        errorResponse.setError_key(errorKey);
        errorResponse.setResource(resource);
        errorResponse.setResource_key(resourceKey);

        return errorResponse;
    }
}

