package com.invillia.challenge.shoppingcart.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<ItemResponseDto> items;
    private BigDecimal total;

    public CartDto() {

    }

    public CartDto(List<ItemResponseDto> items, BigDecimal total) {
        this.items = items;
        this.total = total;
    }

    public List<ItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<ItemResponseDto> items) {
        this.items = items;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                ", listProducts=" + items +
                ", total=" + total +
                '}';
    }
}
