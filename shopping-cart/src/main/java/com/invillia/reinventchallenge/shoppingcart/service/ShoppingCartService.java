package com.invillia.reinventchallenge.shoppingcart.service;

import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoRequest;
import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import com.invillia.reinventchallenge.shoppingcart.entity.User;
import com.invillia.reinventchallenge.shoppingcart.exception.ProductNotFoundException;
import com.invillia.reinventchallenge.shoppingcart.exception.ShoppingCartNotFoundException;
import com.invillia.reinventchallenge.shoppingcart.exception.UserNotFoundException;
import com.invillia.reinventchallenge.shoppingcart.repository.ProductRepository;
import com.invillia.reinventchallenge.shoppingcart.repository.ShoppingCartRepository;
import com.invillia.reinventchallenge.shoppingcart.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ShoppingCart> getAll() {
        log.info("Erika, id={}", 1);
        final List<ShoppingCart> shoppingCartAll = shoppingCartRepository.findAll();

        return StreamSupport.stream(shoppingCartAll.spliterator(), false).collect(Collectors.toList());
    }

    public Product addProduct(Long idUser, String sku, ProductDtoRequest productDto) {
        log.info("addProduct, idUser={}, sku={}, productDto={}", idUser, sku, productDto);

        // TODO: Adiconar validação sku
        final User user = userRepository.findById(idUser)
                .orElseThrow(() -> {
                    log.error("Usuário não encontrado!");
                    return new UserNotFoundException("Usuário não encontrado!", idUser.toString());
                });

        final Product product = new Product(sku, productDto.getName(), productDto.getPrice(), productDto.getQuantity());

        ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);

        if (shoppingCartUser != null){
            log.info("addProduct Existe Shopping Cart na base");
            product.setShoppingCart(shoppingCartUser);
            shoppingCartUser.getListProducts().add(product);
            shoppingCartRepository.save(shoppingCartUser);

            return product;
        }

        shoppingCartUser = new ShoppingCart();
        shoppingCartUser.setUser(user);
        product.setShoppingCart(shoppingCartUser);
        shoppingCartUser.setListProducts(Arrays.asList(product));
        shoppingCartRepository.save(shoppingCartUser);


        return product;
    }

    public Product putProduct(Long idUser, String sku, ProductDtoRequest productDto) {
        log.info("putProductService, idUser={}, sku={}, productDto={}", idUser, sku, productDto);
        final ShoppingCart shoppingCartUser = getShoppingCart(idUser);
        final Product productBySku = getProductBySku(sku, idUser,shoppingCartUser);

        productBySku.setName(productDto.getName());
        productBySku.setPrice(productDto.getPrice());
        productBySku.setQuantity(productDto.getQuantity());
        return productRepository.save(productBySku);

    }

    public Product editNumberOfItem(Long idUser, String sku, Integer quantity) {
        log.info("editNumberOfItem, idUser={}, sku={}, quantity={}", idUser, sku, quantity);
        final ShoppingCart shoppingCartUser = getShoppingCart(idUser);
        final Product productBySku = getProductBySku(sku, idUser, shoppingCartUser);
        productBySku.setQuantity(quantity);
        return productRepository.save(productBySku);
    }

    public Product removeProductShoppingCart(Long idUser, String sku) {
        log.info("removeProductShoppingCart, idUser={}, sku={}", idUser, sku);
        final ShoppingCart shoppingCartUser = getShoppingCart(idUser);
        final Product productBySku = getProductBySku(sku, idUser, shoppingCartUser);
        shoppingCartUser.getListProducts().remove(productBySku);
        shoppingCartRepository.save(shoppingCartUser);
        return productBySku;
    }

    public Product retrieveProductShoppingCart(Long idUser, String sku) {
        log.info("retrieveProductShoppingCart, idUser={}, sku={}", idUser, sku);
        final ShoppingCart shoppingCartUser = getShoppingCart(idUser);
        final Product productBySku = getProductBySku(sku, idUser, shoppingCartUser);
        return productBySku;
    }

    public ShoppingCart retrieveShoppingCart(Long idUser) {
        log.info("retrieveShoppingCart, idUser={}", idUser);
        final ShoppingCart shoppingCartUser = getShoppingCart(idUser);
        return shoppingCartUser;
    }

    public ShoppingCart removeShoppingCart( Long idUser) {
        log.info("removeShoppingCart, idUser={}", idUser);
        final ShoppingCart shoppingCartUser = getShoppingCart(idUser);
        shoppingCartRepository.delete(shoppingCartUser);
        return shoppingCartUser;
    }

    private Product getProductBySku(String sku, Long idUser,ShoppingCart shoppingCartUser) {

        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new ProductNotFoundException("Produto não encontrado!", sku);
        });
        return productBySku.get();
    }

    private ShoppingCart getShoppingCart(Long idUser) {
        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        if (shoppingCartUser == null) {
            log.error("removeProductShoppingCart Carrinho não encontrado! idUser={}", idUser);
            throw new ShoppingCartNotFoundException("Carrinho não encontrado!", idUser.toString());
        }
        return shoppingCartUser;
    }
}
