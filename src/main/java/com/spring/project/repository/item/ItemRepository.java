package com.spring.project.repository.item;

import com.spring.project.entity.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemCustomRepository {
    List<Item> findByTitle(String title);
    Page<Item> findAll(Pageable pageable);
    Page<Item> findByTitleContaining(String keyword, Pageable pageable);
}
