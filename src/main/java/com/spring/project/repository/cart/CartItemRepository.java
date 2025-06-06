package com.spring.project.repository.cart;

import com.spring.project.entity.Cart;
import com.spring.project.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    Optional<CartItem> findByCartIdAndItemId(Long cartId,Long itemId);

    @Query("SELECT ci FROM CartItem ci JOIN FETCH ci.cart JOIN FETCH ci.item WHERE ci.cart.id=:cartId AND ci.item.id=:itemId")
    Optional<CartItem> findByCartIdAndItemId(@Param("cartId") Long cartId, @Param("itemId") Long itemId);

    List<CartItem> findCartItemsByCart(Cart cart);
}
