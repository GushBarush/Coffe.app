package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.Role;
import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepo userRepo;

    public RegistrationController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Map<String, Object> model) {
        User userDBName = userRepo.findByUsername(user.getUsername());

        if (userDBName != null) {
            model.put("message", "Этот номер телефона уже зарегестрирован");
            return "registration";
        }

        user.setNewUserNumber();
        user.setCoffee(0);
        user.setHappyCoffee(0);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);

        return "redirect:/login";
    }
}
