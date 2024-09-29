package com.spring.project.controller;

import com.spring.project.entity.Item;
import com.spring.project.request.ItemDto;
import com.spring.project.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/manager/new")
    public String addItemForm(Model model) {
        model.addAttribute("item", new ItemDto.NewItemDto());
        return "manager/addItem";
    }

    @PostMapping("/manager/new")
    public String addItem(@Validated @ModelAttribute("item") ItemDto.NewItemDto itemDto, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if(itemService.existsItem(itemDto.toEntity())) {
            bindingResult.reject("existsBook");
        }

        if(bindingResult.hasErrors()) {
            return "manager/addItem";
        }

        Item item = itemService.addItem(itemDto.toEntity());
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("save", true);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item=itemService.findById(itemId);

        if(item==null) {
            throw new IllegalStateException("존재하지 않는 도서입니다.");
        } else {
            model.addAttribute("item", item);
        }
        return "items/item";
    }

    @GetMapping("/items")
    public String items(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Item> paging=itemService.findAll(page);
        model.addAttribute("paging", paging);

        return "items/items";
    }
}