package com.invillia.reinvent.challenge.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "User_Id")
    private User user;

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
