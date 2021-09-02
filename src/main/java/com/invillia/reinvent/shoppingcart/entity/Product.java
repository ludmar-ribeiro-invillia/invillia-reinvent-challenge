package com.invillia.reinvent.shoppingcart.entity;

import lombok.ToString;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@ToString(doNotUseGetters = true)
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @ToString.Exclude
    private Integer id;

    private String sku;
    private BigDecimal price;
    private String name;
    private Integer quantity;

    public Product() {}

    public Product(String sku, BigDecimal price, String name, Integer quantity) {
        this.sku = sku;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
