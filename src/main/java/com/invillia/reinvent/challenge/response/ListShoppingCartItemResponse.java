package com.invillia.reinvent.challenge.response;

import java.util.ArrayList;
import java.util.List;

public class ListShoppingCartItemResponse {

    private List<ShoppingCartItemResponse> items;

    private Double total;

    public ListShoppingCartItemResponse() {
    }


    public ListShoppingCartItemResponse(List<ShoppingCartItemResponse> items, Double total) {
        this.items = items;
        this.total = total;
    }

    public List<ShoppingCartItemResponse> getItems() {
        if(items == null){
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<ShoppingCartItemResponse> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
