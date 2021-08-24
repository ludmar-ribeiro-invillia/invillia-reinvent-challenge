package com.invillia.reinventchallenge.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDto {

    @NotEmpty(message = "ShoppingCartDto, User id does not exist!")
    private String userId;


    private List<ProductDtoResponse> listProducts;

}
