package com.company.my_web.controllers;

import com.company.my_web.models.Favorite;
import com.company.my_web.repo.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @GetMapping("/user")
    public String user_account(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Favorite> favorite = favoriteRepository.findAllByAuthorUsername(auth.getName());
        model.addAttribute("list", favorite);
        return "user";
    }
}