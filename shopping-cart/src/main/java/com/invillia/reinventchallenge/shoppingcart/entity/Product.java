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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduct")
    private Long id;
    @Column(nullable = false)
    private String sku;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    private Integer quantity;

    public Product(String sku, String name, BigDecimal price, Integer quantity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @PrePersist
    public void prePersist() {
        if (quantity == null || quantity == 0) {
            quantity = 1;
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (quantity == null || quantity == 0) {
            quantity = 1;
        }
    }

    @ManyToOne
    @JoinColumn(name = "idShoppingCart", referencedColumnName = "idShoppingCart")
    private ShoppingCart shoppingCart;

    public BigDecimal calculateSumProduct() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
