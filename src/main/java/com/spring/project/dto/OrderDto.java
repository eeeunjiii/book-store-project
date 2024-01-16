package com.spring.project.dto;

import com.spring.project.constant.DeliveryStatus;
import com.spring.project.entity.Member;
import com.spring.project.entity.Order;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDto {

    private int total_price;
    private DeliveryStatus delivery_status;
    private LocalDate order_date;
    private Member member;

    public Order toEntity(){
        return Order.builder()
                .total_price(total_price)
                .delivery_status(delivery_status)
                .order_date(order_date)
                .member(member)
                .build();
    }
}
