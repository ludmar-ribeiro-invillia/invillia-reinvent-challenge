package com.invillia.challenge.shoppingcart.service;

import com.invillia.challenge.shoppingcart.dto.CartDto;
import com.invillia.challenge.shoppingcart.dto.ItemDto;
import com.invillia.challenge.shoppingcart.dto.ItemResponseDto;
import com.invillia.challenge.shoppingcart.entity.Cart;
import com.invillia.challenge.shoppingcart.entity.Customer;
import com.invillia.challenge.shoppingcart.entity.Product;
import com.invillia.challenge.shoppingcart.repository.CartRepository;
import com.invillia.challenge.shoppingcart.repository.CustomerRepository;
import com.invillia.challenge.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
        //TODO adicionar validação para sku do produto e userid, adicionar erro, no caso da segunda tentativa, este produto não existe ou este produto já está no carrinho.
        final Customer customer = customerRepository.findById(userId)
                .orElseThrow();
        final Product product = new Product(itemDto.getPrice(), itemDto.getName(), sku);
        final Cart cart = new Cart(product, customer, itemDto.getQuantity(), product.getPrice());
        productRepository.save(product);
        cartRepository.save(cart);
        return new ItemResponseDto(sku, itemDto.getName(), itemDto.getPrice(), itemDto.getQuantity());
    }

    public ItemResponseDto editItem(Integer userId, String sku, ItemDto itemDto) {
        Cart cart = cartRepository.findByProduct_SkuAndCustomer_Id(sku, userId);
        cart.setPrice(itemDto.getPrice());
        cart.setQuantity(itemDto.getQuantity());
        cartRepository.save(cart);
        return new ItemResponseDto(sku, itemDto.getName(), itemDto.getPrice(), itemDto.getQuantity());
    }

    public ItemResponseDto deleteItem(Integer userId, String sku) {
        Cart cart = cartRepository.findByProduct_SkuAndCustomer_Id(sku, userId);
        cartRepository.delete(cart);
        return new ItemResponseDto(sku, cart.getProduct().getName(), cart.getPrice(), cart.getQuantity());
    }

    public ItemResponseDto editQuantity(Integer userId, String sku, Integer quantity) {
        Cart cart = cartRepository.findByProduct_SkuAndCustomer_Id(sku, userId);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
        return new ItemResponseDto(sku, cart.getProduct().getName(), cart.getPrice(), cart.getQuantity());
    }

    public ItemResponseDto getItem(Integer userId, String sku) {
        Cart cart = cartRepository.findByProduct_SkuAndCustomer_Id(sku, userId);
        return new ItemResponseDto(sku, cart.getProduct().getName(), cart.getPrice(), cart.getQuantity());
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
        List<Cart> carts = cartRepository.findByCustomer_Id(userId);
        BigDecimal total = BigDecimal.ZERO;
        List<ItemResponseDto> listItemResponseDto = new ArrayList<ItemResponseDto>();
        for(Cart cart: carts) {
            total = total.add(cart.getPrice().multiply(new BigDecimal(cart.getQuantity())));
            ItemResponseDto itemResponse = new ItemResponseDto(cart.getProduct().getSku(), cart.getProduct().getName(), cart.getPrice(), cart.getQuantity());
            listItemResponseDto.add(itemResponse);
        }
        return new CartDto(listItemResponseDto, total);
    }
}
