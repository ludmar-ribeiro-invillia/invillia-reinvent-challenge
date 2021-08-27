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
        if (shoppingCartUser != null) {
            log.info("addProduct Existe Shopping Cart na base");
            product.setShoppingCart(shoppingCartUser);
            shoppingCartUser.getListProducts().add(product);
            shoppingCartUser = shoppingCartRepository.save(shoppingCartUser);
            return product;
        }

        shoppingCartUser = new ShoppingCart();
        shoppingCartUser.setUser(user);
        product.setShoppingCart(shoppingCartUser);

        log.info("addProduct shoppingCartUser");
        shoppingCartUser.setListProducts(Arrays.asList(product));

        shoppingCartUser = shoppingCartRepository.save(shoppingCartUser);
        log.info("addProduct save shoppingCarUser");


        return shoppingCartUser.getListProducts().get(productDto.getQuantity());
    }

    public Product putProduct(Long idUser, String sku, ProductDtoRequest productDto) {
        log.info("putProductService, idUser={}, sku={}, productDto={}", idUser, sku, productDto);
        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new ProductNotFoundException("Produto não encontrado!", sku);
        });

        final Product product = new Product(sku, productDto.getName(), productDto.getPrice(), productDto.getQuantity());

        return productRepository.save(product);

    }

    public Product editNumberOfItem(Long idUser, String sku, Integer quantity) {
        log.info("editNumberOfItem, idUser={}, sku={}, quantity={}", idUser, sku, quantity);
        ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new ProductNotFoundException("Produto não encontrado!", sku);
        });
        Product product = productBySku.get();
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public Product removeProductShoppingCart(Long idUser, String sku) {
        log.info("removeProductShoppingCart, idUser={}, sku={}", idUser, sku);
        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        if (shoppingCartUser == null) {
            log.error("removeProductShoppingCart Carrinho não encontrado! idUser={}, sku={}", idUser, sku);
            throw new ShoppingCartNotFoundException("Carrinho não encontrado!", idUser.toString());
        }
        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new ProductNotFoundException("Produto não encontrado!", sku);
        });
        final Product product = productBySku.get();
        shoppingCartUser.getListProducts().remove(product);
        shoppingCartRepository.save(shoppingCartUser);
        return product;
    }

    public Product retrieveProductShoppingCart(Long idUser, String sku) {
        log.info("retrieveProductShoppingCart, idUser={}, sku={}", idUser, sku);
        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
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

    public ShoppingCart retrieveShoppingCart(Long idUser) {
        log.info("retrieveShoppingCart, idUser={}", idUser);
        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        if (shoppingCartUser == null) {
            throw new ShoppingCartNotFoundException("Carrinho não encontrado!", idUser.toString());
        }
        return shoppingCartUser;
    }

    public ShoppingCart removeShoppingCart(Long idUser) {
        log.info("removeShoppingCart, idUser={}", idUser);
        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        if (shoppingCartUser == null) {
            log.error("removeShoppingCart, não existe este carrinho de compras, idUser={}", idUser);
            throw new ShoppingCartNotFoundException("Não existe este carrinho de compras!", idUser.toString());
        }
        shoppingCartRepository.delete(shoppingCartUser);
        return shoppingCartUser;
    }

}
