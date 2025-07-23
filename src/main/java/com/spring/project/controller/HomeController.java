package com.spring.project.controller;

import com.spring.project.entity.Item;
import com.spring.project.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final ItemService itemService;

    @GetMapping("/")
    public String index(Model model) {
        Page<Item> paging=itemService.findAll(0);
        model.addAttribute("paging", paging);


        return "index";
    }
}
