package com.spring.project.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Builder
    public CartItem(int quantity, Item item, Cart cart){
        this.quantity=quantity;
        this.item=item;
        this.cart=cart;
    }

    public static CartItem createCartItem(int quantity, Cart cart, Item item) {
        return CartItem.builder()
                .cart(cart)
                .item(item)
                .quantity(quantity)
                .build();
    }

    public void updateCartItem(int quantity) {
        this.quantity=quantity;
    }

    public void addCount(int count) {
        this.quantity=count;
    }
}
