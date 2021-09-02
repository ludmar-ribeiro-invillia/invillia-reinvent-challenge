package com.invillia.reinvent.store.shoppingcart.model;

public class ErrorResponse {
    private String resource;
    private String errorKey;
    private String message;
    private String resourceKey;

    private ErrorResponse(String resource, String errorKey, String message, String resourceKey) {
        this.resource = resource;
        this.errorKey = errorKey;
        this.message = message;
        this.resourceKey = resourceKey;
    }

    public String getResource() {
        return resource;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getMessage() {
        return message;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public static ErrorResponse of(String resource, String errorKey, String message, String resourceKey) {
        return new ErrorResponse(resource, errorKey, message, resourceKey);
    }

}