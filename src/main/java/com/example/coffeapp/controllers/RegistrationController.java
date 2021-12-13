package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.User;
import com.example.coffeapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {

        if (userService.haveUser(user)) {
            model.addAttribute("message", "Этот номер телефона уже зарегестрирован");
            return "registration";
        }

        userService.addUser(user);

        return "redirect:/login";
    }
}
