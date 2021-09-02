package com.invillia.reinvent.store.shoppingcart.repository;

import com.invillia.reinvent.store.shoppingcart.model.ProductItem;
import com.invillia.reinvent.store.shoppingcart.model.ProductItemId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductItemRepository extends CrudRepository<ProductItem, ProductItemId> {

    List<ProductItem> findByUserId(Integer userId);

}