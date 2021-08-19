package com.invillia.reinventchallenge.shoppingcart.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(indexName = "shopping-cart")
public class ShoppingCart{

    @Id
    private String userId;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Product> listProducts;



}
