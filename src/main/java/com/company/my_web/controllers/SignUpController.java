package com.company.my_web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //контроллер это програмный модуль, который получает запрос от пользователя и возвращает данные
public class SignUpController {

    @GetMapping("/sign_up")
    public String sign_up(Model model) {
        return "sign_up";
    }

}