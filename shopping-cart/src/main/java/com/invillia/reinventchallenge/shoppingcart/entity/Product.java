package com.invillia.reinventchallenge.shoppingcart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    private String sku;
    private String name;
    private BigDecimal price;
    private Integer quantity;


}
