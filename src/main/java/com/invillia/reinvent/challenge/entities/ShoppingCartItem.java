package com.invillia.reinvent.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Shopping_cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SKU")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "Shopping_Cart_Id")
    private ShoppingCart shoppingCart;
    private Integer quantity;


    public ShoppingCartItem(){
    }

    public ShoppingCartItem(Long id, Product product, ShoppingCart shoppingCart, Integer quantity) {
        this.id = id;
        this.product = product;
        this.shoppingCart = shoppingCart;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
