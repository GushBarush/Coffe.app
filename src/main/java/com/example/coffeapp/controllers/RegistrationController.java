package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.user.UserDTO;
import com.example.coffeapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {

    final UserService userService;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(UserDTO user, Model model) {

        if (userService.haveUser(user)) {
            model.addAttribute("message", "Этот номер телефона уже зарегестрирован");
            return "registration";
        }

        if (user.getName().equals("") || user.getName() == null) {
            model.addAttribute("messageName", "Неуказанно имя");
            return "registration";
        }

        if(user.getPassword().length() < 8) {
            model.addAttribute("messagePass", "пароль не может быть меньше 8 символов");
            return "registration";
        }

        userService.addUser(user);

        return "redirect:/login";
    }
}
