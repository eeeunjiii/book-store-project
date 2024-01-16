package com.spring.project.dto;

import com.spring.project.entity.Item;
import com.spring.project.entity.Order;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemDto {

    private int order_count;
    private int price;
    private Order order;
    private Item item;
}
