package com.invillia.challenge.shoppingcart.exceptions;

public class ErrorResponse {

    private String resource;
    private String error_key;
    private String message;
    private String resource_key;

    public ErrorResponse(){

    }

    public ErrorResponse(String resource, String error_key, String message, String resource_key) {
        this.resource = resource;
        this.error_key = error_key;
        this.message = message;
        this.resource_key = resource_key;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getError_key() {
        return error_key;
    }

    public void setError_key(String error_key) {
        this.error_key = error_key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResource_key() {
        return resource_key;
    }

    public void setResource_key(String resource_key) {
        this.resource_key = resource_key;
    }
}
