package com.spring.project.request;

import com.spring.project.entity.Item;
import com.spring.project.entity.Order;
import com.spring.project.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDto {

    private int quantity;
    private Long itemId;
    private String title;

    public OrderItem toEntity(Order order, Item item) {
        return OrderItem.builder()
                .item(item)
                .order(order)
                .order_count(quantity)
                .build();
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;
        OrderItemDto that=(OrderItemDto) obj;
        return itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
}
