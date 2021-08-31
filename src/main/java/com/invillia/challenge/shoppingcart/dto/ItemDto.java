package com.invillia.challenge.shoppingcart.dto;

import java.math.BigDecimal;

public class ItemDto {
    private String name;
    private BigDecimal price;
    private Integer quantity = 1;

    public ItemDto() {

    }

    public ItemDto(String name, BigDecimal price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ItemDto(String name, BigDecimal price) {
        this(name, price, 1);
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
