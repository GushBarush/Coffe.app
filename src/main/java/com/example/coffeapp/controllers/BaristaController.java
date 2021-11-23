package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/barista")
@PreAuthorize("hasAuthority('ADMIN')")
public class BaristaController {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());

        return "baristapage";
    }

    @GetMapping("add_bonus/{user}")
    public String addBonus(@PathVariable User user) {
        user.addCoffee();

        userRepo.save(user);

        return "redirect:/barista";
    }

    @GetMapping("del_bonus/{user}")
    public String delBonus(@PathVariable User user) {
        user.delHappyCoffee();

        userRepo.save(user);

        return "redirect:/barista";
    }
}
