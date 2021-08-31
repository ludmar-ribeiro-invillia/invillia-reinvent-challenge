package com.invillia.reinvent.challenge.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

public class StandardError implements Serializable {


    private String resource;
    private String errorKey;
    private String message;
    private String resourceKey;

    public StandardError(){

    }

    public StandardError(String resource, String errorKey, String message, String resourceKey) {
        this.resource = resource;
        this.errorKey = errorKey;
        this.message = message;
        this.resourceKey = resourceKey;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }
}
