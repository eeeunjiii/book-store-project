package com.spring.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private Integer publish_year;
    private Integer price;
    private Integer stock;

    @Builder
    public Item(Long id, String title, String author, String publisher, Integer publish_year, Integer price, Integer stock){
        this.id=id;
        this.title=title;
        this.author=author;
        this.publisher=publisher;
        this.publish_year=publish_year;
        this.price=price;
        this.stock=stock;
    }

    public void updateItem(String title, String author, Integer price, Integer stock) {
        this.title=title;
        this.author=author;
        this.price=price;
        this.stock=stock;
    }
}
