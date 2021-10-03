package com.company.my_web.controllers;

import com.company.my_web.models.Role;
import com.company.my_web.models.User;
import com.company.my_web.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller //контроллер это програмный модуль, который получает запрос от пользователя и возвращает данные
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign_up")
    public String sign_up(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() != "anonymousUser")
            model.addAttribute("menu", "blocks/user_header");
        else
            model.addAttribute("menu", "blocks/header");
        return "sign_up";
    }

    @PostMapping("/sign_up")
    public String AddUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("error", "Логин уже занят.");
            return "sign_up";
        }

        String pattern = "(\\w{4,})";
        if (!user.getUsername().matches(pattern)) {
            model.addAttribute("error", "Логин должен состоять минимум из 4 символов.");
            return "sign_up";
        }

        if (!user.getPassword().matches(pattern)) {
            model.addAttribute("error", "Пароль должен состоять минимум из 8 символов.");
            return "sign_up";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "comment_success";

    }
}