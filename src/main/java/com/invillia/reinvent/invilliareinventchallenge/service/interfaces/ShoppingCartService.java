package com.invillia.reinvent.invilliareinventchallenge.service.interfaces;

import com.invillia.reinvent.invilliareinventchallenge.entity.Item;
import com.invillia.reinvent.invilliareinventchallenge.entity.ShoppingCart;
import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingCartRecord;
import com.invillia.reinvent.invilliareinventchallenge.record.ShoppingRecordTotal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    Optional<ShoppingCart> findByUser(Long userId);

    ShoppingCart save(ShoppingCart shopCart);

    ShoppingCartRecord save(Long userId, Long productId, Item itemUser);

    ShoppingCartRecord editProduct(Long userId, Long productId, Item itemUser);

    ShoppingCartRecord editByQuantity(Long userId, Long productId,String qtd);

    List<ShoppingCart> findAllByUser(Long userId);

    ShoppingCartRecord deleteItem(String userId, String productId);

    void deleteById(Long userId);

    ShoppingRecordTotal findByShoppingCartByUser (Long userId);

    ShoppingRecordTotal deleteShoppingCartByUserId( Long userId);

    ShoppingCartRecord findProductByCartUser(Long userId, Long productId);

    //Optional<Item> findAllByProductId(Long userId, Long productId);
}
