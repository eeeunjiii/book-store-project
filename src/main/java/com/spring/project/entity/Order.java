package com.spring.project.entity;

import com.spring.project.constant.DeliveryStatus;
import com.spring.project.constant.Payment;
import com.spring.project.request.OrderInfoDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int total_price;
    private DeliveryStatus delivery_status;

    private LocalDateTime order_date;
    @PrePersist
    public void createAt() {
        this.order_date=LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems=new ArrayList<>();

    private String customer;
    private String memo;
    private String phoneNumber;
    private String destination;
    private int point;
    private Payment payment;

    @Builder
    public Order(Long id, int total_price, DeliveryStatus delivery_status,
                 User user, String customer, String memo, String phoneNumber,
                 String destination, int point, Payment payment){
        this.id=id;
        this.total_price=total_price;
        this.delivery_status=delivery_status;
        this.user = user;
        this.customer=customer;
        this.memo=memo;
        this.phoneNumber=phoneNumber;
        this.destination=destination;
        this.point=point;
        this.payment=payment;
    }

    public static Order createOrder(User user) {
        return Order.builder()
                .user(user)
                .delivery_status(DeliveryStatus.PREPARING)
                .total_price(0)
                .build();
    }

    public void updateOrderInfo(OrderInfoDto orderInfo) {
        Optional.ofNullable(orderInfo.getCustomer()).ifPresent(none -> this.customer=orderInfo.getCustomer());
        Optional.ofNullable(orderInfo.getMemo()).ifPresent(none ->  this.memo= orderInfo.getMemo());
        Optional.ofNullable(orderInfo.getPhoneNumber()).ifPresent(none -> this.phoneNumber= orderInfo.getPhoneNumber());
        Optional.ofNullable(orderInfo.getDestination()).ifPresent(none -> this.destination= orderInfo.getDestination());
        Optional.of(orderInfo.getPoint()).ifPresent(none -> this.point=orderInfo.getPoint());
        Optional.ofNullable(orderInfo.getPayment()).ifPresent(none -> this.payment=orderInfo.getPayment());
    }

    public void updateTotalPrice(int totalPrice) {
        this.total_price=totalPrice;
    }
}
