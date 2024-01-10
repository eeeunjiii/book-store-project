package com.spring.project.entity;

import jakarta.persistence.*;

@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cart_count;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
