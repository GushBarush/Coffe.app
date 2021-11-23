package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainUserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String main(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userRepo.findByUsername(user.getUsername()));

        return "mainPage";
    }
}
