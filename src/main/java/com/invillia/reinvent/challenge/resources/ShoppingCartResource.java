package com.invillia.reinvent.challenge.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shoppingcart")
public class ShoppingCartResource {

    @GetMapping
    public String helloWorld(){
        return "Hello World";
    }
}
