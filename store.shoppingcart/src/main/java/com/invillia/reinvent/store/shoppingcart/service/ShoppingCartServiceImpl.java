package com.invillia.reinvent.store.shoppingcart.service;

import com.invillia.reinvent.store.shoppingcart.exception.ProductItemNotFoundException;
import com.invillia.reinvent.store.shoppingcart.exception.ShoppingCartNotFoundException;
import com.invillia.reinvent.store.shoppingcart.model.ProductItem;
import com.invillia.reinvent.store.shoppingcart.model.ProductItemId;
import com.invillia.reinvent.store.shoppingcart.model.ShoppingCart;
import com.invillia.reinvent.store.shoppingcart.repository.ProductItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

    private final ProductItemRepository repository;

    public ShoppingCartServiceImpl(
            ProductItemRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public ProductItem addProductItem(ProductItem productItem) {
        return repository.save(productItem);
    }

    @Override
    public ProductItem setProductItem(ProductItem productItem) {
        Optional<ProductItem> result = repository.findById (
                ProductItemId.of(
                        productItem.getUserId(),
                        productItem.getSku()
                )
        );

        ProductItem oldProductItem = result.get();

        if(result.isEmpty())
            throw new ProductItemNotFoundException(
                    "There's no product for the given user id and sku",
                    productItem.getSku()+"@"+productItem.getUserId()
            );

        if(oldProductItem.getPrice() == null)
            throw new ProductItemNotFoundException(
                    "There's no product for the given user id and sku",
                    productItem.getSku()+"@"+productItem.getUserId()
            );

        return repository.save(productItem);
    }

    @Override
    public ProductItem getProductItem(Integer userId, Integer sku) {
        Optional<ProductItem> result = repository.findById (
                ProductItemId.of(
                        userId,
                        sku
                )
        );

        if(result.isEmpty())
            throw new ProductItemNotFoundException(
                    "There's no product for the given user id and sku",
                    sku+"@"+userId
            );

        ProductItem productItem = result.get();

        return productItem;
    }

    @Override
    public ShoppingCart getShoppingCart(Integer userId) {
        List<ProductItem> items = repository.findByUserId(userId);

        if(items.isEmpty())
            throw new ShoppingCartNotFoundException(
                    "There's no Shopping Cart for the given user id"
            );

        BigDecimal total = items.stream().map((item) -> item.getPrice().multiply(new BigDecimal(item.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);

        return ShoppingCart.of(items, total);
    }

    @Override
    public ProductItem setProductItemQuantity(Integer userId, Integer sku, Integer quantity) {
        Optional<ProductItem> result = repository.findById (
                ProductItemId.of(
                        userId,
                        sku
                )
        );

        if(result.isEmpty())
            throw new ProductItemNotFoundException(
                    "There's no product for the given user id and sku",
                    sku+"@"+userId
            );

        ProductItem productItem = result.get();

        if(quantity == null) quantity = 1;
        productItem.setQuantity(quantity);

        return repository.save(productItem);
    }

    @Override
    public ProductItem deleteProductItem (Integer userId, Integer sku) {
        Optional<ProductItem> result = repository.findById (
                ProductItemId.of(
                        userId,
                        sku
                )
        );

        if(result.isEmpty())
            throw new ProductItemNotFoundException(
                    "There's no product for the given user id and sku",
                    sku+"@"+userId
            );

        ProductItem productItem = result.get();

        repository.delete(productItem);
        return productItem;
    }

    @Override
    public ShoppingCart deleteShoppingCart (Integer userId) {
        // --- busca os itens do carrinho ---
        List<ProductItem> items = repository.findByUserId(userId);

        if(items.isEmpty())
            throw new ShoppingCartNotFoundException(
                    "There's no Shopping Cart for the given user id"
            );

        //--- deleta os itens do carrinho ---
        items.forEach((item) -> deleteProductItem(item.getUserId(), item.getSku()));
        BigDecimal total = null;

        return ShoppingCart.of(items, total);
    }
}