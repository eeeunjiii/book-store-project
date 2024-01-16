package com.spring.project.entity;

import com.spring.project.constant.DeliveryStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int total_price;
    private DeliveryStatus delivery_status;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate order_date;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private Member member;

    @Builder
    public Order(int total_price, DeliveryStatus delivery_status, LocalDate order_date, Member member){
        this.total_price=total_price;
        this.delivery_status=delivery_status;
        this.order_date=order_date;
        this.member=member;
    }
}
