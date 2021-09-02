package com.invillia.reinvent.store.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;

@Entity
@IdClass(ProductItemId.class)
public class ProductItem {

    @Id
    private Integer userId;

    @Id
    private Integer sku;

    private BigDecimal price;
    private String name;
    private Integer quantity;

    public ProductItem() {}

    public ProductItem(
            Integer userId,
            Integer sku,
            BigDecimal price,
            String name,
            Integer quantity
    ) {
        this.userId = userId;
        this.sku = sku;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
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

}