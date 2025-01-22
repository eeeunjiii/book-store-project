package com.spring.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @RequestMapping(path={"/login"})
    public String login(Model model, HttpServletRequest request) {
        String referrer=request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);

        return "login";
    }
}
