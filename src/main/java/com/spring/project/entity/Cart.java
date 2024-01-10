package com.spring.project.entity;

import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int total_price;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
