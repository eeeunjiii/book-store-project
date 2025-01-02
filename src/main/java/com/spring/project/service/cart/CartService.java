package com.spring.project.service.cart;

import com.spring.project.entity.Cart;
import com.spring.project.entity.CartItem;
import com.spring.project.entity.Item;
import com.spring.project.entity.User;
import com.spring.project.repository.cart.CartRepository;
import com.spring.project.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final ItemService itemService;
    private final CartItemService cartItemService;

    public void addItemToCart(User user, Item item, int quantity) {
        Cart cart=findCartByUserId(user);

        if(cart==null) {
            cart=Cart.createCart(user);
            save(cart);
        }

        Item findItem=itemService.findById(item.getId());
        CartItem cartItem=cartItemService.findByCartIdAndItemId(cart.getId(), findItem.getId());

        if(cartItem==null) {
            cartItem=CartItem.createCartItem(quantity, cart, item);
            cartItemService.save(cartItem);
        } else {
            cartItem.updateCartItem(cartItem.getQuantity()+quantity);
            cartItemService.save(cartItem);
        }
        updateCartTotalQuantity(cart, quantity);
    }

    private Cart findCartByUserId(User user) {
        return cartRepository.findByUserId(user.getId());
    }

    private void save(Cart cart) {
        cartRepository.save(cart);
    }

    private void updateCartTotalQuantity(Cart cart, int quantity) {
        Cart.updateCart(cart, quantity);
    }
}
