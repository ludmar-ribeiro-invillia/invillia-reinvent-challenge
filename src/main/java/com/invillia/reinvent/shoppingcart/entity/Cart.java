package com.invillia.reinvent.shoppingcart.entity;

import lombok.ToString;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ToString.Exclude
    private Integer id;

    @ToString.Exclude
    private Integer idUser;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> product;

    public Cart(){ }

    public Cart(Integer idUser, List<Product> product) {
        this.idUser = idUser;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public void addProduct(Product product) {
        this.product.add(product);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (Product product : product) {
            total = total.add(product.getPrice().multiply(new BigDecimal(product.getQuantity())));
        }
        return total;
    }
}
