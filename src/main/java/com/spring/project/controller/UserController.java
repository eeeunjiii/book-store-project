package com.spring.project.controller;

import com.spring.project.request.JoinDto;
import com.spring.project.request.LoginDto;
import com.spring.project.service.member.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "join";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("joinDto") JoinDto joinDto, BindingResult bindingResult) {
        if(userService.checkDuplicatedUserName(joinDto.getName())) {
            bindingResult.addError(new FieldError("joinDto", "name", "이미 존재하는 이름입니다."));
        }

        if(userService.findUserByEmail(joinDto.getEmail())!=null) {
            bindingResult.addError(new FieldError("joinDto", "email", "이미 가입한 이메일입니다."));
        }

        if(!joinDto.getPassword().equals(joinDto.getCheckPassword())) {
            bindingResult.addError(new FieldError("joinDto", "checkPassword", "비밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "join";
        }

        userService.join(joinDto);

        return "redirect:/login";
    }
}
