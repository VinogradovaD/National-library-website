package com.company.my_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //контроллер это програмный модуль, который получает запрос от пользователя и возвращает данные
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}