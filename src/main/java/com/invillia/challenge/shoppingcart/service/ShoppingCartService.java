package com.invillia.challenge.shoppingcart.service;

import com.invillia.challenge.shoppingcart.dto.CartDto;
import com.invillia.challenge.shoppingcart.dto.ItemDto;
import com.invillia.challenge.shoppingcart.dto.ItemResponseDto;
import com.invillia.challenge.shoppingcart.entity.Cart;
import com.invillia.challenge.shoppingcart.entity.Customer;
import com.invillia.challenge.shoppingcart.entity.Product;
import com.invillia.challenge.shoppingcart.exceptions.*;
import com.invillia.challenge.shoppingcart.repository.CartRepository;
import com.invillia.challenge.shoppingcart.repository.CustomerRepository;
import com.invillia.challenge.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ShoppingCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProducts() {
        Iterable<Product> productIterable = productRepository.findAll();
        return StreamSupport.stream(productIterable.spliterator(),false).collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public ItemResponseDto AddItem(Integer userId, String sku, ItemDto itemDto) {
        final Customer customer = validateCustomer(userId);
        final Product product = validateProduct(sku);
        validateRepeatedCartProduct(sku, userId);
        final Cart cart = new Cart(product, customer, itemDto.getQuantity());
        product.setPrice(itemDto.getPrice());
        productRepository.save(product);
        cartRepository.save(cart);
        return new ItemResponseDto(sku, cart.getProduct().getName(), cart.getProduct().getPrice(), cart.getQuantity());
    }

    public ItemResponseDto editItem(Integer userId, String sku, ItemDto itemDto) {
        Cart cart = validateEmptyCartProduct(sku, userId);
        cart.getProduct().setPrice(itemDto.getPrice());
        cart.setQuantity(itemDto.getQuantity());
        productRepository.save(cart.getProduct());
        cartRepository.save(cart);
        return new ItemResponseDto(sku, cart.getProduct().getName(), cart.getProduct().getPrice(), cart.getQuantity());
    }

    public ItemResponseDto deleteItem(Integer userId, String sku) {
        Cart cart = validateEmptyCartProduct(sku, userId);
        cartRepository.delete(cart);
        return new ItemResponseDto(sku, cart.getProduct().getName(), cart.getProduct().getPrice(), cart.getQuantity());
    }

    public ItemResponseDto getItem(Integer userId, String sku) {
        Cart cart = validateEmptyCartProduct(sku, userId);
        return new ItemResponseDto(sku, cart.getProduct().getName(), cart.getProduct().getPrice(), cart.getQuantity());
    }

    public CartDto getCart(Integer userId) {
        return getCartByUserId(userId);
    }

    public CartDto deleteCart(Integer userId) {
        final CartDto cart = getCartByUserId(userId);
        cartRepository.deleteByCustomer_Id(userId);
        return cart;
    }

    public CartDto getCartByUserId(Integer userId) {
        List<Cart> carts = validateCart(userId);
        BigDecimal total = BigDecimal.ZERO;
        List<ItemResponseDto> listItemResponseDto = new ArrayList<ItemResponseDto>();
        for(Cart cart: carts) {
            total = total.add(cart.getProduct().getPrice().multiply(new BigDecimal(cart.getQuantity())));
            ItemResponseDto itemResponse = new ItemResponseDto(cart.getProduct().getSku(), cart.getProduct().getName(), cart.getProduct().getPrice(), cart.getQuantity());
            listItemResponseDto.add(itemResponse);
        }
        return new CartDto(listItemResponseDto, total);
    }

    public Customer validateCustomer(Integer userId) {
        Optional<Customer> customer = customerRepository.findById(userId);
        if(customer.isPresent()) {
            return customer.get();
        } else {
            throw new CustomerNotFoundException("Customer not found, please validate the id sent on the request.", userId.toString());
        }
    }

    public Product validateProduct(String sku) {
        Product product = productRepository.findBySku(sku);
        if(product == null) {
            throw new ProductNotFoundException("Product sku not found, please validate the sku sent on the request", sku);
        }
        return product;
    }

    public void validateRepeatedCartProduct(String sku, Integer userId) {
        final Cart cart = cartRepository.findByProduct_SkuAndCustomer_Id(sku, userId);
        if(cart != null) {
            throw new CartAlreadyHasProductException("The shopping cart already have the same product.", sku);
        }
    }

    public Cart validateEmptyCartProduct(String sku, Integer userId) {
        final Cart cart = cartRepository.findByProduct_SkuAndCustomer_Id(sku, userId);
        if(cart == null) {
            throw new CartProductNotFoundException("There is no product on shopping cart for the given SKU.", sku);
        } else {
            return cart;
        }
    }

    public List<Cart> validateCart(Integer userId) {
        final List<Cart> cart = cartRepository.findByCustomer_Id(userId);
        if(cart == null) {
            throw new CartNotFoundException("There is no shopping cart for the given Costumer Id", userId.toString());
        } else {
            return cart;
        }
    }

}
