package com.spring.project.repository.item;

import com.spring.project.entity.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByTitle(String title);
    List<Item> findByAuthor(String author);
    Page<Item> findAll(Pageable pageable);
}
