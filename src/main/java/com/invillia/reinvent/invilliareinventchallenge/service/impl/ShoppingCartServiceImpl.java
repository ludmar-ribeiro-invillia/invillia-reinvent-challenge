package com.invillia.reinvent.invilliareinventchallenge.service.impl;

import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import com.invillia.reinvent.invilliareinventchallenge.entity.Product;
import com.invillia.reinvent.invilliareinventchallenge.entity.ShoppingCart;
import com.invillia.reinvent.invilliareinventchallenge.entity.User;
import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingCartRecord;
import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingRecordTotal;
import com.invillia.reinvent.invilliareinventchallenge.repository.ItemRepository;
import com.invillia.reinvent.invilliareinventchallenge.repository.ShoppingCartRepository;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.ProductService;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.ShoppingCartService;
import com.invillia.reinvent.invilliareinventchallenge.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemRepository itemRepository;


    public ShoppingCartRecord save(Long userId, Long productId, Item itemUser) {

        Optional<User> usu = Optional.of(userService.findById(userId).get());
        Optional<Product> prod = Optional.of(productService.findById(productId).get());
        Optional<ShoppingCart> shopCart = shoppingCartRepository.findByUser(usu.get().getId());
        Item item = new Item();
        Optional<Item> i = this.itemRepository.findAllByProductId(usu.get().getId(), prod.get().getId());
        if (!shopCart.isPresent()) {
            shopCart = Optional.of(new ShoppingCart());
        }
        if (i.isPresent()) {
            i.get().setQuantity(i.get().getQuantity() + 1);
            this.itemRepository.save(i.get());
            return ShoppingCartMapShoppingCartRecord(shopCart.get(), i.get());
        } else {

            if (itemUser.getQuantity() == null) {
                item.setQuantity(1);
            }
            item.setQuantity(itemUser.getQuantity());
            item.setProduct(prod.get());
            shopCart.get().setUser(usu.get());
            item.setCart(shoppingCartRepository.save(shopCart.get()));
            itemRepository.save(item);
            return ShoppingCartMapShoppingCartRecord(shopCart.get(), item);

        }
    }

    public ShoppingCartRecord editProduct(Long userId, Long productId, Item itemUser) {

        Optional<User> usu = Optional.of(userService.findById(userId).get());
        Optional<Product> prod = Optional.of(productService.findById(productId).get());

        Optional<ShoppingCart> cart = shoppingCartRepository.findByUser(usu.get().getId());

        Optional<Item> i = this.itemRepository.findAllByProductId(usu.get().getId(), prod.get().getId());

        i.get().setQuantity(itemUser.getQuantity());

        if (itemUser.getQuantity() == null) {
            i.get().setQuantity(1);
        } else {
            i.get().setQuantity(itemUser.getQuantity());
        }
        i.get().setProduct(prod.get());
        this.itemRepository.save(i.get());

        return ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
    }

    public ShoppingCartRecord editByQuantity(Long userId, Long productId,String qtd) {

        Optional<ShoppingCart> cart = shoppingCartRepository.findByUser(Long.parseLong(String.valueOf(userId)));
        Optional<Item> i = this.itemRepository.findAllByProductId(Long.parseLong(String.valueOf(userId)), Long.parseLong(String.valueOf(productId)));
        i.get().setQuantity(Integer.parseInt(qtd));
        this.itemRepository.save(i.get());
        return ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
    }

    public ShoppingCart save(ShoppingCart sc) {
        return this.shoppingCartRepository.save(sc);
    }


    public Optional<ShoppingCart> findByUser(Long userId) {
        Optional<ShoppingCart> u = this.shoppingCartRepository.findByUser(userId);
        return u;
    }


    public List<ShoppingCart> findAllByUser(Long userId) {
        return this.shoppingCartRepository.findAllByUser(userId);
    }

    public ShoppingCartRecord deleteItem(String userId, String productId){
        Optional<Item> i = this.itemRepository.findAllByProductId(Long.parseLong(userId), Long.parseLong(productId));
        if(i.isPresent()){
            Optional<ShoppingCart> cart = shoppingCartRepository.findByUser(Long.parseLong(userId));
            ShoppingCartRecord scr = ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
            this.itemRepository.deleteById(i.get().getId());
            return scr;
        }
        return null;
    }

    public ShoppingRecordTotal findByShoppingCartByUser(Long userId) {
        List<Item> itens = this.itemRepository.findAllByUser(Long.parseLong(String.valueOf(userId)));
        Optional<ShoppingCart> cart = shoppingCartRepository.findByUser(Long.parseLong(String.valueOf(userId)));
        if (cart.get().getItens() == null) {
            cart.get().setItens(new ArrayList<>());
        }
        BigDecimal tot = new BigDecimal(0);
        cart.get().setItens(itens);
        for (int i = 0; i < cart.get().getItens().size(); i++) {
            tot = tot.add(cart.get().getItens().get(i).getProduct().getPrice());
        }
        cart.get().setTotal(tot);
        return ShoppingCartMapShoppingRecordTotal(cart.get());
    }

    public void deleteById(Long shopCartId) {


        this.shoppingCartRepository.deleteAll();
    }

    public ShoppingCartRecord findProductByCartUser(Long userId, Long productId) {
        Optional<Item> i = this.itemRepository.findAllByProductId(Long.parseLong(String.valueOf(userId)), Long.parseLong(String.valueOf(productId)));
        Optional<ShoppingCart> cart = shoppingCartRepository.findByUser(Long.parseLong(String.valueOf(userId)));

        return ShoppingCartMapShoppingCartRecord(cart.get(), i.get());
    }

    public ShoppingRecordTotal deleteShoppingCartByUserId(@PathVariable("userId") Long userId) {

        List<Item> itens = this.itemRepository.findAllByUser(Long.parseLong(String.valueOf(userId)));
        Optional<ShoppingCart> cart = shoppingCartRepository.findByUser(Long.parseLong(String.valueOf(userId)));
        if (cart.get().getItens() == null) {
            cart.get().setItens(new ArrayList<>());
        }
        BigDecimal tot = new BigDecimal(0);
        cart.get().setItens(itens);
        for (int i = 0; i < cart.get().getItens().size(); i++) {
            tot = tot.add(cart.get().getItens().get(i).getProduct().getPrice());
        }
        cart.get().setTotal(tot);
        ShoppingRecordTotal sct = ShoppingCartMapShoppingRecordTotal(cart.get());
        shoppingCartRepository.deleteById(cart.get().getId());
        for (int i = 0; i < itens.size(); i++) {
            this.itemRepository.deleteById(itens.get(i).getId());
        }
        return sct;

    }

    public ShoppingCartRecord ShoppingCartMapShoppingCartRecord(ShoppingCart sc, Item item) {
        return new ShoppingCartRecord(sc.getId(),
                item.getProduct().getPrice(),
                item.getProduct().getDescription(),
                item.getQuantity());
    }

    public ShoppingRecordTotal ShoppingCartMapShoppingRecordTotal(ShoppingCart sc) {
        return new ShoppingRecordTotal(sc.getId(),
                sc.getItens(),
                sc.getTotal());
    }


}
