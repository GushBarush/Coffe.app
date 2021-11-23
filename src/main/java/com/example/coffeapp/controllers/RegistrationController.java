package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.Role;
import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Map<String, Object> model) {
        User userDBEmail = userRepo.findByEmail(user.getEmail());
        User userDBName = userRepo.findByUsername(user.getUsername());

        if (userDBEmail != null) {
            model.put("message", "Этот Email уже зарегестрирорван");
            return "registration";
        }

        if (userDBName != null) {
            model.put("message", "Этот номер телефона уже зарегестрирован");
            return "registration";
        }

        user.setCoffee((long)0);
        user.setHappyCoffee((long)0);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
