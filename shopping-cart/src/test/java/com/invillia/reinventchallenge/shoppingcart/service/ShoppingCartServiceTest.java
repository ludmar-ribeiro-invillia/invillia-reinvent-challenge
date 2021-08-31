package com.invillia.reinventchallenge.shoppingcart.service;

import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoRequest;
import com.invillia.reinventchallenge.shoppingcart.exception.UserNotFoundException;
import com.invillia.reinventchallenge.shoppingcart.repository.ProductRepository;
import com.invillia.reinventchallenge.shoppingcart.repository.ShoppingCartRepository;
import com.invillia.reinventchallenge.shoppingcart.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class ShoppingCartServiceTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createShoppingCart(){

        final ProductDtoRequest productDtoRequest = new ProductDtoRequest();
        productDtoRequest.setQuantity(2);
        productDtoRequest.setPrice(new BigDecimal(18.00));
        productDtoRequest.setName("Café");

        shoppingCartService.addProduct(1234L, "sku123", productDtoRequest);
    }


    @Test
    public void shoppingCartException(){
        final ProductDtoRequest productDtoRequest = new ProductDtoRequest();
        productDtoRequest.setQuantity(2);
        productDtoRequest.setPrice(new BigDecimal(18.00));
        productDtoRequest.setName("Café");

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            shoppingCartService.addProduct(1234L, "sku123", productDtoRequest);
        });
    }

}

