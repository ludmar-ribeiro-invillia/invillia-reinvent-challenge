package com.invillia.reinventchallenge.shoppingcart.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String resource;
    private String error_key;
    private String message;
    private String resource_key;

}
