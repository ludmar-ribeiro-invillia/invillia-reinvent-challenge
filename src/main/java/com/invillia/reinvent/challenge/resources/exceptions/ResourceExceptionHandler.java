package com.invillia.reinvent.challenge.resources.exceptions;

import com.invillia.reinvent.challenge.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){


        String error = "Resource not found";
        HttpStatus status= HttpStatus.NOT_FOUND;
        StandardError err = new StandardError("User", "user.not.found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
