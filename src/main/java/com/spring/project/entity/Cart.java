package com.spring.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Cart")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int total_price;

    @OneToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems=new ArrayList<>();

    /*@Builder
    public Cart(Long id, int total_price, Member member){
        this.id=id;
        this.total_price=total_price;
        this.member=member;
    }*/
}
