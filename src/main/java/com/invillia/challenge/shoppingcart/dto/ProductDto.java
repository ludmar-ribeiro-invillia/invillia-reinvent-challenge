package com.invillia.challenge.shoppingcart.dto;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private BigDecimal price;

    public ProductDto() {

    }

    public ProductDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
