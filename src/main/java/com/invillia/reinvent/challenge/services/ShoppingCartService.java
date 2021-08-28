package com.invillia.reinvent.challenge.services;

import com.invillia.reinvent.challenge.entities.Product;
import com.invillia.reinvent.challenge.entities.ShoppingCart;
import com.invillia.reinvent.challenge.entities.ShoppingCartItem;
import com.invillia.reinvent.challenge.entities.User;
import com.invillia.reinvent.challenge.repositories.ShoppingCartItemRepository;
import com.invillia.reinvent.challenge.repositories.ShoppingCartRepository;
import com.invillia.reinvent.challenge.request.AddShoppingCartItemRequest;
import com.invillia.reinvent.challenge.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ShoppingCartService {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartItemRepository itemRepository;

    public void addItem(Long userId, Long sku, AddShoppingCartItemRequest request){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart;
        if(optShoppingCart.isPresent()){
            shoppingCart = optShoppingCart.get();
        } else {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCartRepository.save(shoppingCart);
        }

        Product product = productService.findById(sku);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setQuantity(request.getQuantity());
        shoppingCartItem.setProduct(product);
        shoppingCartItem.setShoppingCart(shoppingCart);

        itemRepository.save(shoppingCartItem);
    }

    public void removeItem(Long userId, Long sku){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = new ShoppingCart();
        if(optShoppingCart.isPresent()){
            shoppingCart = optShoppingCart.get();
        } else {
            optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));
        }

        Product product = productService.findById(sku);

        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setShoppingCart(shoppingCart);
        shoppingCartItem.setProduct(product);

        itemRepository.delete(shoppingCartItem);

    }

    public void findById(Long userId){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart;
        if(optShoppingCart.isPresent()){
            shoppingCart = optShoppingCart.get();
            shoppingCart.getTotal();
        } else {
            optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));
        }

    }

    public void removeShoppingCart(Long userId){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart;
        if(optShoppingCart.isPresent()){
            shoppingCart = optShoppingCart.get();
            itemRepository.deleteById(userId);
        }
        else {
            optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));
        }



    }

}
