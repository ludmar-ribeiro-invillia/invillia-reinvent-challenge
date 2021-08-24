package com.invillia.reinventchallenge.shoppingcart.service;

import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoRequest;
import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import com.invillia.reinventchallenge.shoppingcart.entity.User;
import com.invillia.reinventchallenge.shoppingcart.exception.ShoppingCartNotFoundException;
import com.invillia.reinventchallenge.shoppingcart.repository.ProductRepository;
import com.invillia.reinventchallenge.shoppingcart.repository.ShoppingCartRepository;
import com.invillia.reinventchallenge.shoppingcart.repository.UserRepository;
import javassist.NotFoundException;
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

    public List<ShoppingCart> getShoppingCartAll(){
        log.info("Erika, id={}",1);
        final Iterable<ShoppingCart> shoppingCartAll = shoppingCartRepository.findAll();

        return StreamSupport.stream(shoppingCartAll.spliterator(),false).collect(Collectors.toList());
    }

    public Product addProduct(Long idUser, String sku, ProductDtoRequest productDto) throws NotFoundException {
        log.info("addProduct, idUser={}, sku={}, productDto={}", idUser, sku, productDto);

        // TODO: Adiconar validação sku
        final User user = userRepository.findById(idUser)
                .orElseThrow(() -> {
                    log.error("Usuário não encontrado!");
                    return new NotFoundException("Usuário não encontrado!");
                } );

        final Product product = new Product();
        product.setSku(sku);
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());

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

        log.info("addProduct shoppingCartUser");
        shoppingCartUser.setListProducts(Arrays.asList(product));

        shoppingCartRepository.save(shoppingCartUser);
        log.info("addProduct save shoppingCarUser");


        return product;
    }

    public Product putProductService(Long idUser, String sku, ProductDtoRequest productDto) throws NotFoundException {
        log.info("putProductService, idUser={}, sku={}, productDto={}", idUser, sku, productDto);
        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);

        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new NotFoundException("Produto não encontrado!");
        } );

        final Product product = productBySku.get();
        product.setQuantity(productDto.getQuantity());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());

        return productRepository.save(product);
    }

    public Product editNumberOfItem(Long idUser, String sku, Integer quantity ) throws NotFoundException {
        log.info("editNumberOfItem, idUser={}, sku={}, quantity={}", idUser, sku, quantity);

        ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new NotFoundException("Produto não encontrado!");
        } );

        Product product = productBySku.get();
        product.setQuantity(quantity);


        return productRepository.save(product);
    }

    public Product removeProductShoppingCart(Long idUser, String sku) throws NotFoundException {
        log.info("removeProductShoppingCart, idUser={}, sku={}", idUser, sku);

        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);
        if (shoppingCartUser == null){
            log.error("removeProductShoppingCart Carrinho não encontrado! idUser={}, sku={}", idUser, sku);
            throw new NotFoundException("Carrinho não encontrado!");
        }

        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new NotFoundException("Produto não encontrado!");

        } );
        final Product product = productBySku.get();
        shoppingCartUser.getListProducts().remove(product);
        shoppingCartRepository.save(shoppingCartUser);

        return product;
    }

    public Product retrieveProductShoppingCart(Long idUser, String sku) throws NotFoundException {
        log.info("retrieveProductShoppingCart, idUser={}, sku={}", idUser, sku);

        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);

        final Optional<Product> productBySku = shoppingCartUser.getListProducts()
                .stream()
                .filter(item -> sku.equals(item.getSku()))
                .findFirst();

        productBySku.orElseThrow(() -> {
            log.error("Produto não encontrado!");
            return new NotFoundException("Produto não encontrado!");
        });

        return productBySku.get();
    }

    public ShoppingCart retrieveShoppingCart(Long idUser) throws NotFoundException {
        log.info("retrieveShoppingCart, idUser={}", idUser);

        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);

        if (shoppingCartUser == null) {
            throw new NotFoundException("Carrinho não encontrado!");
        }

        return shoppingCartUser;
    }

    public ShoppingCart removeShoppingCart(Long idUser) throws NotFoundException {
        log.info("removeShoppingCart, idUser={}", idUser);


        final ShoppingCart shoppingCartUser = shoppingCartRepository.findByUserId(idUser);

        if (shoppingCartUser == null){
            log.error("removeShoppingCart, não existe este carrinho de compras, idUser={}", idUser);
            throw new ShoppingCartNotFoundException("Não existe este carrinho de compras!");
        }

        shoppingCartRepository.delete(shoppingCartUser);

        return shoppingCartUser;
    }


}
