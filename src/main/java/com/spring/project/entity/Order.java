package com.spring.project.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int total_price;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate order_date;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
