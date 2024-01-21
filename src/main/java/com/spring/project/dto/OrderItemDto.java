package com.spring.project.dto;

import com.spring.project.entity.Item;
import com.spring.project.entity.Order;
import com.spring.project.entity.OrderItem;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemDto {
    private Long id;
    private int order_count;
    private int price;
    private Order order;
    private Item item;

    private OrderItem toEntity(){
        return OrderItem.builder()
                .id(id)
                .order_count(order_count)
                .price(price)
                .order(order)
                .item(item)
                .build();
    }
}
