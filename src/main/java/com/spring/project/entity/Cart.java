package com.spring.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems=new ArrayList<>();

    @Builder
    public Cart(int quantity, User user){
        this.quantity=quantity;
        this.user = user;
    }

    public static Cart createCart(User user) {
        return Cart.builder()
                .quantity(0)
                .user(user)
                .build();
    }

    public void updateCart(int quantity) {
        this.quantity+=quantity;
    }
}
