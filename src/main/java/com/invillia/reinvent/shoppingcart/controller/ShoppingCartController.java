/**
 * Controller API para o carrinho de compras
 *
 * @author  Ana K Gimenez
 * @version 1.0
 * @since   2021
 */
package com.invillia.reinvent.shoppingcart.controller;

import com.invillia.reinvent.shoppingcart.entity.Item;
import com.invillia.reinvent.shoppingcart.entity.ShoppingCart;
import com.invillia.reinvent.shoppingcart.repository.ItemRepository;
import com.invillia.reinvent.shoppingcart.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shopping-cart")
public class ShoppingCartController {
@Autowired
    private final ItemRepository repoItem;
@Autowired
    private final ShoppingCartRepository repoCart;

/* para uso do Log */
//    private static Logger LOG = LoggerFactory
//            .getLogger(ShoppingCartController.class);
/* ---------------*/

    ShoppingCartController(ItemRepository repoItem, ShoppingCartRepository repoCart) {
        this.repoItem = repoItem;
        this.repoCart = repoCart;
    }

    private ShoppingCart getCartFromDB(String userId) {
 /* Mock Data */
        ShoppingCart cart = new ShoppingCart(userId);
        cart.addItem("123", "Produto X", 20.5);
        cart.addItem("456", "Produto Y",76.00,2);
        /* -------- */
        return cart;
    }

    private Item getItemFromDB(String userId, String sku) {
        /* Mock Data */
        Item it = new Item("123", "Produto X", 20.5);
        /* -------- */
        return it;
    }

    private boolean addItemToDB(String userId, Item it) {
        return true;
    }

    private void updateCartInDB(String userid,double total) {

    }

    private boolean updateItemInDB(String userId, String sku) {
         return true;
    }

    private boolean deleteCartFromDB(String userId){
        return true;
    }

    private boolean deleteItemFromDB(String userId, String sku) {
        return true;
    }

    private boolean deleteAllItemsByIdFromDB(String userId){
        return true;
    }

    /** Recupera o carrinho de compra do UserId
     * GET /shopping-cart/<user-id>
     * Body None
     * Response OK 200
     * { items: [ {sku, price, name, quantity}, {}, ... ], total }
     */
//    @RequestMapping(value="/{userId}",  method = RequestMethod.GET)
    @GetMapping("/{userId}")
    public ShoppingCart getAllItemInCart(@PathVariable String userId) {
        ShoppingCart cart = getCartFromDB(userId);
        return cart;
}

    /** Recupera um produto do carrinho de compra
     * GET /shopping-cart/<user-id>/items/<SKU>
     * Body None
     * Response OK 200
     * { sku, price, name, quantity }
     */
    @GetMapping("/{userId}/items/{sku}")
    public Item getItemInCartById(@PathVariable String userId, @PathVariable String sku) {
        ShoppingCart cart = getCartFromDB(userId);
        if (cart == null) {
            System.out.println("carrinho não encontrado");
            return null; //Erro
        }
        Item it = getItemFromDB(userId,sku);
        if (it == null) {
            System.out.println("Item não encontrado");
            return null; //Erro
        }
        return it;
    }

    /** Adiciona itens no carrinho do UserId
     * POST /shopping-cart/<user-id>/items/<SKU>
     *
     * Body { price, name, quantity //optional, default value 1 }
     * Response CREATED 201
     * { sku, price, name, quantity }
     */
 //   @RequestMapping(value="/{userId}/items/{sku}", method = RequestMethod.POST)
    @PostMapping("/{userId}/items/{sku}")
    public Item postItemInCartById(@PathVariable String userId, @PathVariable String sku) {
        ShoppingCart cart = getCartFromDB(userId);
        System.out.println("teste ......");
        if (cart == null) {
            System.out.println("carrinho não encontrado");
            return null; //Erro
        }
        Item it = getItemFromDB(userId,sku);
        if (it == null) {
            /* Mock Data */
            cart.addItem("123", "Produto X", 20.5);
            addItemToDB(userId,it);
           /* -------- */
        } else {
            /* Mock Data */
            cart.updateItemInCart("123","banana", 3.50);
            updateItemInDB(userId,sku);
            /* -------- */
        }
        updateCartInDB(userId,cart.getTotal());
        return it;
    }

    /** Edita itens
     * PUT /shopping-cart/<user-id>/items/<SKU>
     *
     * Body { price, name, quantity //optional, default value 1 }
     * Response OK 200
     * { sku, price, name, quantity }
     */
    @PutMapping("/{userId}/items/{sku}")
    public Item editItemById(@PathVariable String userId, @PathVariable String sku) {
        ShoppingCart cart = getCartFromDB(userId);
        if (cart == null) {
            System.out.println("carrinho não encontrado");
            return null;
        }
        Item it = getItemFromDB(userId,sku);
        if (it == null) {
            /* Mock Data */
            cart.addItem("123", "Produto X", 20.5);
            addItemToDB(userId,it);
            /* -------- */
        } else {
            /* Mock Data */
            cart.updateItemInCart("123","banana", 3.50);
            updateItemInDB(userId,sku);
            /* -------- */
        }
        updateCartInDB(userId,cart.getTotal());
        return it;
    }

    /** Edita a quantidade de itens
     * PATCH /shopping-cart/<user-id>/items/<SKU>?quantity=<new quantity>
     *
     * Body None
     * Response OK 200
     * { sku, price, name, quantity }
     */
    @PatchMapping("/{userId}/items/{sku}")
       public Item editItemWithQuantity(@PathVariable String userId, @PathVariable String sku, @RequestParam(required = false) int quantity) {
        ShoppingCart cart = getCartFromDB(userId);
        System.out.println("entrou no patch");

        if (cart == null) {
            System.out.println("carrinho não encontrado");
            return null;
        }
        Item it = getItemFromDB(userId,sku);
        if (it == null) {
            /* Mock Data */
            cart.addItem("123", "Produto X", 20.5,quantity);
            addItemToDB(userId,it);
            /* -------- */
        } else {
            /* Mock Data */
            cart.updateItemInCart("123","banana", 3.50,quantity);
            updateItemInDB(userId,sku);
            /* -------- */
        }
        updateCartInDB(userId,cart.getTotal());
        return it;
    }

    /** Remover um produto do carrinho
     * DELETE /shopping-cart/<user-id>/items/<SKU>
     * Body None
     * Response OK 200
     * { sku, price, name, quantity }
     */
    @DeleteMapping("/{userId}/items/{sku}")
    public Item deleteItemInCart(@PathVariable String userId, @PathVariable String sku) {
        ShoppingCart cart = getCartFromDB(userId);
        if (cart == null) {
            System.out.println("carrinho não encontrado");
            return null;
        }
        Item it = getItemFromDB(userId,sku);
        if (it == null) {
            System.out.println("item não encontrado");
            return null;
        }
        cart.removeItemInCart(sku);
        deleteItemFromDB(userId,sku);
        updateCartInDB(userId,cart.getTotal());
        return it;
    }

    /** Remover o carrinho de compra
     * DELETE /shopping-cart/<user-id>
     * Body None
     * Response OK 200
     * { items: [ {sku, price, name, quantity}, {}, ... ], total }
     */
    @RequestMapping(value="/{userId}",  method = RequestMethod.DELETE)
    public ShoppingCart deleteAllItemInCart(@PathVariable String userId) {
        ShoppingCart cart = getCartFromDB(userId);
        if (cart == null) {
            System.out.println("carrinho não encontrado");
            return null; //Erro
        }
        deleteAllItemsByIdFromDB(userId);
        deleteCartFromDB(userId);
        return cart;
    }
    /*  Error message
    Response Any 4XX or 5XX errors
    {
        "resource":"<resource name>",
            "error_key":"<error type key>",
            "message":"<error message>",
            "resource_key":"<resource id>"
    }
    Ex: Response RESOURCE NOT FOUND 404

    {
        "resource":"product",
            "error_key":"product.not.found",
            "message":"There is no product on shopping cart for the given SKU.",
            "resource_key":"ASDFGHJK"
    } */
}


