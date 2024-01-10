package com.spring.project.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;
    private String address;
    private String phone_number;
    private int point;

    @OneToMany
    private List<Order> orders=new ArrayList<>(); // Order와의 양방향 매핑을 위해 추가

    @OneToOne(mappedBy = "member")
    private Cart cart;

}
