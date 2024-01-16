package com.spring.project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "Item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private int publish_year;
    private int price;
    private int stock;

    @Builder
    public Item(String title, String author, String publisher, int publish_year, int price, int stock){
        this.title=title;
        this.author=author;
        this.publisher=publisher;
        this.publish_year=publish_year;
        this.price=price;
        this.stock=stock;
    }
}
