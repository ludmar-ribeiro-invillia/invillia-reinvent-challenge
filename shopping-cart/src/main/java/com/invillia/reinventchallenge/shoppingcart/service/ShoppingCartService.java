package com.invillia.reinventchallenge.shoppingcart.service;

import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import com.invillia.reinventchallenge.shoppingcart.repository.ShoppingCartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getShoppingCartAll(){
        log.info("Erika, id={}",1);
        final Iterable<ShoppingCart> shoppingCartAll = shoppingCartRepository.findAll();

        return StreamSupport.stream(shoppingCartAll.spliterator(),false).collect(Collectors.toList());
    }


}
