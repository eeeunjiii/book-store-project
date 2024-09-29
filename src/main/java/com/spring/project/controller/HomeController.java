package com.spring.project.controller;

import com.spring.project.entity.Item;
import com.spring.project.entity.User;
import com.spring.project.security.PrincipalDetails;
import com.spring.project.service.item.ItemService;
import com.spring.project.service.member.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserService userService;
    private final ItemService itemService;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principal) {
        Page<Item> paging=itemService.findAll(0);
        model.addAttribute("paging", paging);

        if(principal!=null) {
            User user=userService.findUserByEmail(principal.getUsername());
            if(user!=null) {
                model.addAttribute("email", user.getEmail());
            }
        }
        return "index";
    }
}
