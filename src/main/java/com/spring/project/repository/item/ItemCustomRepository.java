package com.spring.project.repository.item;

import com.spring.project.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemCustomRepository {

    Page<Item> search(String category, String keyword, Pageable pageable);
}
