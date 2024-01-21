package com.spring.project.dto;

import com.spring.project.entity.Cart;
import com.spring.project.entity.Member;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CartDto {
    private Long id;
    private int total_price;
    private Member member;

    public Cart toEntity(){
        return Cart.builder()
                .id(id)
                .total_price(total_price)
                .member(member)
                .build();
    }
}
