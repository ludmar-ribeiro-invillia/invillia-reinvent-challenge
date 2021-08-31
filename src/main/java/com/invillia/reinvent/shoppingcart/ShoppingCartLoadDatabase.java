package com.invillia.reinvent.shoppingcart;


import com.invillia.reinvent.shoppingcart.entity.Item;
import com.invillia.reinvent.shoppingcart.entity.ShoppingCart;
import com.invillia.reinvent.shoppingcart.repository.ItemRepository;
import com.invillia.reinvent.shoppingcart.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ShoppingCartLoadDatabase {

    private static final Logger log =
            LoggerFactory.getLogger(ShoppingCartLoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ShoppingCartRepository repoCart, ItemRepository repoItem) {
       ShoppingCart cart = new ShoppingCart("1111");
       List<Item> l = cart.getItems();
       cart.addItem("123", "Produto X", 2.50, 1);
        return args -> {

//            log.info("Cart " + repoCart.save(cart));
      //      log.info("Item" + repoCart.save(cart));

            log.info("Saved ");
        };
    }
}
