package com.invillia.reinventchallenge.shoppingcart.service;

import com.invillia.reinventchallenge.shoppingcart.dto.ProductDtoRequest;
import com.invillia.reinventchallenge.shoppingcart.entity.Product;
import com.invillia.reinventchallenge.shoppingcart.entity.ShoppingCart;
import com.invillia.reinventchallenge.shoppingcart.entity.User;
import com.invillia.reinventchallenge.shoppingcart.repository.ShoppingCartRepository;
import com.invillia.reinventchallenge.shoppingcart.repository.UserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;

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


}
