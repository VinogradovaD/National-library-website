package com.company.my_web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    @GetMapping("/")
    public String root(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() != "anonymousUser")
            model.addAttribute("menu", "blocks/user_header");
        else
            model.addAttribute("menu", "blocks/header");
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() != "anonymousUser")
            model.addAttribute("menu", "blocks/user_header");
        else
            model.addAttribute("menu", "blocks/header");
        return "home";
    }
}