package com.spring.project.repository.cart;

import com.spring.project.entity.Cart;
import com.spring.project.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartIdAndItemId(Long cartId, Long itemId);

    List<CartItem> findCartItemsByCart(Cart cart);
}
