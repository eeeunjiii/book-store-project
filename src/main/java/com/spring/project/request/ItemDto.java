package com.spring.project.request;

import com.spring.project.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class ItemDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class NewItemDto {

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        @NotBlank
        private String publisher;

        @NotNull
        private Integer publish_year;

        @NotNull
        private Integer price;

        @NotNull
        private Integer stock;

        @Builder
        public NewItemDto(String title, String author, String publisher, Integer publish_year, Integer price, Integer stock) {
            this.title = title;
            this.author = author;
            this.publisher = publisher;
            this.publish_year = publish_year;
            this.price = price;
            this.stock = stock;
        }

        public Item toEntity() {
            return Item.builder()
                    .title(this.title)
                    .author(this.author)
                    .publisher(this.publisher)
                    .publish_year(this.publish_year)
                    .price(this.price)
                    .stock(this.stock)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UpdateItemDto {

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        @NotNull
        private Integer price;

        @NotNull
        private Integer stock;

        @Builder
        public UpdateItemDto(String title, String author, Integer price, Integer stock) {
            this.title=title;
            this.author=author;
            this.price = price;
            this.stock = stock;
        }
    }
}
