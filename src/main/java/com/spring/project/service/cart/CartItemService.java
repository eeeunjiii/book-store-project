package com.spring.project.service.cart;

import com.spring.project.entity.CartItem;
import com.spring.project.repository.cart.CartItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public CartItem findByCartIdAndItemId(Long cartId, Long itemId) {
         return cartItemRepository.findByCartIdAndItemId(cartId, itemId)
                .orElse(null);
    }
}
