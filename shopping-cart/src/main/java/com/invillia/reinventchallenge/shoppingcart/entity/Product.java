package com.invillia.reinventchallenge.shoppingcart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProduct")
    private Long id;
    @Column(unique = true, nullable = false)
    private String sku;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "idShoppingCart")
    private ShoppingCart shoppingCart;


}
