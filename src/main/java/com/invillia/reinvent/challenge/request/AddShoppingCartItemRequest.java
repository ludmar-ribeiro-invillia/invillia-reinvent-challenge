package com.invillia.reinvent.challenge.request;

public class AddShoppingCartItemRequest {

    private Double price;
    private String name;
    private Integer quantity = 1;

    public AddShoppingCartItemRequest(){

    }

    public AddShoppingCartItemRequest(Double price, String name, Integer quantity) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
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
