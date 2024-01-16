package com.spring.project.dto;

import com.spring.project.entity.Item;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDto {

    private String title;
    private String author;
    private String publisher;
    private int publish_year;
    private int price;
    private int stock;

    public Item toEntity(){
        return Item.builder()
                .title(title)
                .author(author)
                .publisher(publisher)
                .publish_year(publish_year)
                .price(price)
                .stock(stock)
                .build();
    }
}
