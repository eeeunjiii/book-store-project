package com.spring.project.entity;

import com.spring.project.constant.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int order_count;
    private int price;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    /*@Builder
    public OrderItem(Long id, int order_count, int price, Order order, Item item){
        this.id=id;
        this.order_count=order_count;
        this.price=price;
        this.order=order;
        this.item=item;
    }*/
}
