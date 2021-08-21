package com.invillia.reinventchallenge.shoppingcart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUser")
    private Long id;
    @Column(nullable = false, length = 250)
    private String name;
    @Column(nullable = false, length = 11)
    private String cpf;


    @OneToOne(mappedBy = "user")
    private ShoppingCart shoppingCart;

}
