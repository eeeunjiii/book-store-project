package com.spring.project.controller;

import com.spring.project.entity.User;
import com.spring.project.security.PrincipalDetails;
import com.spring.project.service.member.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttribute {

    private final UserService userService;

    @ModelAttribute("user")
    public User loginUser(@AuthenticationPrincipal PrincipalDetails principal) {
        if(principal!=null) {
            User user=userService.findUserByEmail(principal.getUsername());
            if(user!=null) {
                return user;
            }
        }
        return null;
    }
}
