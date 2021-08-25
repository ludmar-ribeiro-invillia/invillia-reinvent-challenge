package com.invillia.reinventchallenge.shoppingcart.dto;

import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDtoResponse {

    private String sku;
    private String name;
    private BigDecimal price;
    private Integer quantity;


}
