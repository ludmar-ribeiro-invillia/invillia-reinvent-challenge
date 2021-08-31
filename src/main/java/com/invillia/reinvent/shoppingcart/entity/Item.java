package com.invillia.reinvent.shoppingcart.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEMS")
public class Item {
    @Id
    @Column(name = "sku")
    private String sku;

    @Column(nullable=false)
    private String name;

    @Column(nullable=true)
    private int quantity;

    @Column(nullable=false)
    private double price;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private ShoppingCart cart;

    public Item(){}

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Item(String sku, String name, int quantity, double price) {
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Item(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.quantity = 1;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
