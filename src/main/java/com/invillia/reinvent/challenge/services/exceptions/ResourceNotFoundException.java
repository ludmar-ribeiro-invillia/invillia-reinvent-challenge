package com.invillia.reinvent.challenge.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){

        super("There is no user registered with this ID " + id );
    }
}
