package com.spring.project.service.item;

import com.spring.project.entity.Item;
import com.spring.project.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.spring.project.request.ItemDto.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item addItem(Item item) { // ADMIN
        return itemRepository.save(item);
    }

    public boolean existsItem(Item item) {
        List<Item> findItem=itemRepository.findByTitle(item.getTitle());

        return !findItem.isEmpty();
    }

    @Transactional
    public void updateItemInfo(Long itemId, UpdateItemDto updateItemDto) { // ADMIN
        Item item=itemRepository.findById(itemId)
                .orElseThrow(NoSuchElementException::new);

        item.updateItem(updateItemDto.getTitle(), updateItemDto.getAuthor(), updateItemDto.getPrice(), updateItemDto.getStock());
    }

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElse(null);
    }

    public Page<Item> findAll(int page) {
        Pageable pageable= PageRequest.of(page, 10);
        return itemRepository.findAll(pageable);
    }

    public synchronized void reduceItemStock(Item item, int quantity) {
        int newStock;
        if(item.getStock()-quantity>=0) {
            newStock=item.getStock()-quantity;
        } else {
            newStock=item.getStock();
        }
        item.updateItem(item.getTitle(), item.getAuthor(), item.getPrice(), newStock);
    }

    @Transactional
    public Page<Item> search(String category, String keyword, Pageable pageable) {
        Page<Item> items=itemRepository.search(category, keyword, pageable);
        return items;
    }
}
