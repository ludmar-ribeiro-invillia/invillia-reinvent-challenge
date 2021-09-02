package com.invillia.reinvent.store.shoppingcart.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private List<ProductItem> items = Collections.emptyList();
    private BigDecimal total;

    private ShoppingCart(List<ProductItem> items, BigDecimal total) {
        this.items = items;
        this.total = total;
    }

    public List<ProductItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public static ShoppingCart of(List<ProductItem> items, BigDecimal total) {

        return new ShoppingCart(items, total);
    }
}
