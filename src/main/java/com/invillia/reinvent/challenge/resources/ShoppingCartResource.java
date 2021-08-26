package com.invillia.reinvent.challenge.resources;

import com.invillia.reinvent.challenge.entities.Product;
import com.invillia.reinvent.challenge.entities.ShoppingCart;
import com.invillia.reinvent.challenge.entities.User;
import com.invillia.reinvent.challenge.request.AddShoppingCartItemRequest;
import com.invillia.reinvent.challenge.response.ShoppingCartItemResponse;
import com.invillia.reinvent.challenge.services.ProductService;
import com.invillia.reinvent.challenge.services.ShoppingCartService;
import com.invillia.reinvent.challenge.services.UserService;
import com.invillia.reinvent.challenge.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(value = "/shopping-cart")
public class ShoppingCartResource {

    @Autowired
    private ShoppingCartService service;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @PostMapping("{userId}/items/{sku}")
    public ResponseEntity<ShoppingCartItemResponse> addItem(@PathVariable Long userId, @PathVariable Long sku,
                                                            @RequestBody AddShoppingCartItemRequest request){

        // adicionar item/produto{sku} no carrinho{userId}
        // criar a classe ShoppingCart service -- ok
        // criar metodo addItem parametros (return dele SHoppingCartItem)
        // buscar e validar o usuario
        // buscar shoppingCart(entidade) do usuario, se nao encontrar criar
        // buscar o produto. Se nao encontrar cria
        // instanciar uma nova entity de ShoppingCartItem com tudo o que tem aqui
        // ShoppingCartItemRepository.save(linha24);
        // depois do AddItem dentro do meu resource, montar resposta

        User user = userService.findById(userId);
        if(user == null){
            throw new ResourceNotFoundException(userId);
        }

        Product product = productService.findById(sku);
        if(product == null){
            throw new ResourceNotFoundException(sku);
        }

        ShoppingCart cart =


        }





        return null;
    }
}
