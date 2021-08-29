package com.invillia.challenge.shoppingcart.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sku", referencedColumnName = "sku", nullable = false)
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    private Integer quantity;

    public Cart() {
    }

    public Cart(Product product, Customer customer, Integer quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                ", quantity=" + quantity +
                '}';
    }
}
