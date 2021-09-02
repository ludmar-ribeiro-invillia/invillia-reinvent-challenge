package com.invillia.reinvent.store.shoppingcart.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductItemId implements Serializable {

    private Integer userId;
    private Integer sku;

    public ProductItemId() {}

    private ProductItemId(
            Integer userId,
            Integer sku
    ) {
        this.userId = userId;
        this.sku = sku;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getSku() {
        return sku;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItemId)) return false;
        ProductItemId that = (ProductItemId) o;
        return getUserId().equals(that.getUserId()) && getSku().equals(that.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getSku());
    }

    public static ProductItemId of(Integer userId, Integer sku) {
        return new ProductItemId(userId, sku);
    }
}