package com.invillia.reinvent.challenge.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("There is no resource registered with this ID " + id );
    }

    public ResourceNotFoundException(){
        super("Not found Resource ");
    }
}
