package com.invillia.reinventchallenge.shoppingcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoRequest {

    @NotEmpty(message = "O nome não pode ser nulo!")
    private String name;

    @NotNull(message = "O preço não pode ser nulo!")
    private BigDecimal price;

    private Integer quantity;


}
