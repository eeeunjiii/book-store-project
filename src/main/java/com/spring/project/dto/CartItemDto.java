package com.spring.project.dto;

import com.spring.project.entity.Cart;
import com.spring.project.entity.CartItem;
import com.spring.project.entity.Item;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItemDto {

    private int cart_count;
    private Item item;
    private Cart cart;

    public CartItem toEntity(){
        return CartItem.builder()
                .cart_count(cart_count)
                .item(item)
                .cart(cart)
                .build();
    }
}
