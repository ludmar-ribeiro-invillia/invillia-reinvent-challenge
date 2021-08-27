package com.invillia.reinvent.challenge.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ShoppingCartItemResponse {


    @JsonInclude(Include.NON_NULL)
    private Long sku;
    private Double price;
    private String name;
    private Integer quantity;

    public ShoppingCartItemResponse(){

    }

    public ShoppingCartItemResponse(Long sku, Double price, String name, Integer quantity) {
        this.sku = sku;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
}
