package com.invillia.challenge.shoppingcart.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private Integer userId;
    private List<ItemResponseDto> listProducts;
    private BigDecimal total;

    public CartDto() {

    }

    public CartDto(Integer userId, List<ItemResponseDto> listProducts, BigDecimal total) {
        this.userId = userId;
        this.listProducts = listProducts;
        this.total = total;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ItemResponseDto> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<ItemResponseDto> listProducts) {
        this.listProducts = listProducts;
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
                "userId='" + userId + '\'' +
                ", listProducts=" + listProducts +
                ", total=" + total +
                '}';
    }
}
