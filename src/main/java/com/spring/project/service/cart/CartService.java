package com.spring.project.service.cart;

import com.spring.project.entity.*;
import com.spring.project.repository.cart.CartRepository;
import com.spring.project.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final ItemService itemService;
    private final CartItemService cartItemService;

    @Transactional
    public void addItemToCart(User user, Item item, int quantity) {
        Cart cart=findCartByUserId(user.getId());

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
        save(cart);
    }

    @Transactional
    public void removeOrderedItemFromCart(Order order, User user) {
        Cart cart=user.getCart();
        List<OrderItem> orderItems=order.getOrderItems();

        for(OrderItem orderItem:orderItems) {
            CartItem cartItem=cartItemService.findByCartIdAndItemId(cart.getId(), orderItem.getItem().getId());
            cart.getCartItems().remove(cartItem);
            cartItemService.delete(cartItem);
        }
    }

    @Transactional
    public Cart findCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    private void save(Cart cart) {
        cartRepository.save(cart);
    }

    private void updateCartTotalQuantity(Cart cart, int quantity) {
        cart.updateCart(quantity);
    }
}
