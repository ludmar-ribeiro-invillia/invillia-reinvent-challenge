package com.invillia.reinvent.invilliareinventchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer quantity;

    @JsonIgnoreProperties("cart")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ShoppingCart cart;

    @JsonIgnoreProperties("itens")
    @OneToOne
    private Product product;

}


