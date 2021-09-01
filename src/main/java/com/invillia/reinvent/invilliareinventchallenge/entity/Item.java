package com.invillia.reinvent.invilliareinventchallenge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "items")
public class Item extends Product {

    private Integer quantity;

    private Item(String sku, String name, BigDecimal price, Integer quantity) {
        super(sku, name, price);
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        if (quantity == null) {
            quantity = 1;
        }
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
