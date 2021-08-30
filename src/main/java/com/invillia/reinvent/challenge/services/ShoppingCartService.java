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

import java.util.List;
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

    public ShoppingCartItem addItem(Long userId, Long sku, AddShoppingCartItemRequest request){

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

        Optional<ShoppingCartItem> optShoppingCartItem = itemRepository.findByProductSkuAndShoppingCart(sku, shoppingCart);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        if(optShoppingCartItem.isPresent()){
            shoppingCartItem = optShoppingCartItem.get();
            shoppingCartItem.setQuantity(shoppingCartItem.getQuantity() + request.getQuantity());
        } else{
            shoppingCartItem.setQuantity(request.getQuantity());
        }

        shoppingCartItem.setProduct(product);
        shoppingCartItem.setShoppingCart(shoppingCart);

        itemRepository.save(shoppingCartItem);
        return shoppingCartItem;
    }

    public ShoppingCartItem removeItem(Long userId, Long sku){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));

        Optional<ShoppingCartItem> optShoppingCartItem = itemRepository.findByProductSkuAndShoppingCart(sku, shoppingCart);
        ShoppingCartItem shoppingCartItem = optShoppingCartItem.orElseThrow(() -> new ResourceNotFoundException());

        itemRepository.delete(shoppingCartItem);
        return shoppingCartItem;

    }

    public List<ShoppingCartItem> removeAll(Long userId){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));

        List<ShoppingCartItem> items = itemRepository.findByShoppingCart(shoppingCart);

        itemRepository.deleteAll(items);
        return items;

    }

    public List<ShoppingCartItem> listShoppingCart(Long userId){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));

        List<ShoppingCartItem> items = itemRepository.findByShoppingCart(shoppingCart);

        return items;
    }

    public ShoppingCartItem getItem(Long userId, Long sku){


        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));

        Optional<ShoppingCartItem> optShoppingCartItem = itemRepository.findByProductSkuAndShoppingCart(sku, shoppingCart);
        ShoppingCartItem shoppingCartItem = optShoppingCartItem.orElseThrow(() -> new ResourceNotFoundException());


        return shoppingCartItem;
    }

    public ShoppingCartItem editQuantity(Long userId, Long sku, Integer quantity){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));

        Optional<ShoppingCartItem> optShoppingCartItem = itemRepository.findByProductSkuAndShoppingCart(sku, shoppingCart);
        ShoppingCartItem shoppingCartItem = optShoppingCartItem.orElseThrow(() -> new ResourceNotFoundException());

        shoppingCartItem = optShoppingCartItem.get();
        shoppingCartItem.setQuantity(quantity);

        itemRepository.save(shoppingCartItem);
        return shoppingCartItem;
    }

    public ShoppingCartItem editItem(Long userId, Long sku, AddShoppingCartItemRequest request){

        User user = userService.findById(userId);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepository.findByUserId(userId);
        ShoppingCart shoppingCart = optShoppingCart.orElseThrow(() -> new ResourceNotFoundException(userId));

        Optional<ShoppingCartItem> optShoppingCartItem = itemRepository.findByProductSkuAndShoppingCart(sku, shoppingCart);
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        if(optShoppingCartItem.isPresent()){
            shoppingCartItem = optShoppingCartItem.get();
            shoppingCartItem.setQuantity(request.getQuantity());
        } else{
            optShoppingCartItem.orElseThrow(() -> new ResourceNotFoundException());

        }
        itemRepository.save(shoppingCartItem);
        return shoppingCartItem;
    }

}
