package com.invillia.reinvent.shoppingcart.entity;


import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CART")
public class ShoppingCart {

    @Id
    private String userId;

    @Column(nullable=false)
    private double total;

    @OneToMany(mappedBy="cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;


    public ShoppingCart(String userId) {
        setUserId(userId);
        this.items = new ArrayList<>();
        this.total= 0.0;
    }
    public ShoppingCart(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    private void setItems(List<Item> items) {
        if (items != null) {
            this.items = items;
            setTotal(calcNewTotal(items));
        } else {
            setTotal(0.0);
        }
    }

    public double getTotal() {
        return total;
    }

    private void setTotal(double total) {
        this.total = total;
    }

    public @Nullable
    Item getItemById(String sku) {
        /* Retorna item pelo Sku ou null se não encontrar */
        List<Item> list = getItems();

        int idx = list.indexOf(sku);
        if (idx == -1) {
            return null;
        }
        return list.get(idx);
    }

    private double calcNewTotal(@NotNull List<Item> items) {
        /* Calcula total do carrinho somando os valores dos items */
        double totalAux = 0.0;
        for (Item t : items)
            totalAux += t.getSubTotal();
        return totalAux;
    }

    public void updateItemNewTotal(@NotNull Item it, double newPrice) {
        /* Atualiza Preco, Quantidade do Item e o Total do Carrinho */
        double oldTotal = getTotal();
        double oldSubTotal = it.getSubTotal();

        it.setPrice(newPrice);
        this.total = oldTotal - oldSubTotal + it.getSubTotal();
    }

    public void updateItemNewTotal(@NotNull Item it, double newPrice, int newQtd) {
        /* Atualiza Preco, Quantidade do Item e o Total do Carrinho */
        double oldTotal = getTotal();
        double oldSubTotal = it.getSubTotal();

        it.setPrice(newPrice);
        it.setQuantity(newQtd);
        this.total = oldTotal - oldSubTotal + it.getSubTotal();
    }

    public void updateItemInCart(String sku, String newName, double newPrice) {
        /* Interface: atualiza item se existir */
        Item it = getItemById(sku);
        if (it == null) {
            return;
        }
        updateItemNewTotal(it, newPrice);
        it.setName(newName);
    }

    public void updateItemInCart(String sku, String newName, double newPrice, int newQuantity) {
        /* Interface: atualiza item se existir */
        Item it = getItemById(sku);
        if (it == null) {
            return;
        }
        updateItemNewTotal(it, newPrice, newQuantity);
        it.setName(newName);
    }

    public void addItem(String sku, String name, double price) {
        /* Interface: adiciona item se não existir */
        Item i = getItemById(sku);
        if (i != null) {
            return;
        }

        Item it = new Item(sku, name, 1, price);
        this.items.add(it);
        this.total += it.getSubTotal();
    }

    public void addItem(String sku, String name, double price, int quantity) {
        /* Interface: adiciona item se não existir */
        Item i = getItemById(sku);
        if (i != null) {
            return;
        }

        Item it = new Item (sku, name, quantity, price);
        this.items.add(it);
        this.total += it.getSubTotal();
    }

    public void removeItemInCart(String sku) {
        /* Interface: remove item se existir */
        Item it = getItemById(sku);
        if (it == null) {
            return;
        }
        updateItemNewTotal(it, 0, 0);
        this.items.remove(it);

    }

    public void removeAllItemInCart() {
        /* Interface: remove todos os itens do carrinho */
        if (this.items.size() == 0)
            return;

        for (Item t : this.items)
            this.items.remove(t);

        setTotal(0.0);

    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                 ", userId='" + userId + '\'' +
                ", items=" + items +
                ", total=" + total +
                '}';
    }
}
