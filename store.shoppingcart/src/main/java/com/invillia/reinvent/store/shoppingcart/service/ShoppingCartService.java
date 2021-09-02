package com.invillia.reinvent.store.shoppingcart.service;

import com.invillia.reinvent.store.shoppingcart.model.ProductItem;
import com.invillia.reinvent.store.shoppingcart.model.ShoppingCart;

public interface ShoppingCartService {

    ProductItem addProductItem(
            ProductItem productItem
    );

    ProductItem setProductItem(
            ProductItem productItem
    );

    ProductItem getProductItem(Integer userId, Integer sku);

    ShoppingCart getShoppingCart(Integer userId);

    ProductItem setProductItemQuantity(Integer userId, Integer sku, Integer quantity);

    ProductItem deleteProductItem(Integer userId, Integer sku);

    ShoppingCart deleteShoppingCart (Integer userId);
}