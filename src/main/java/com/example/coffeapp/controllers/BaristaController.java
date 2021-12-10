package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.User;
import com.example.coffeapp.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/barista")
@PreAuthorize("hasAuthority('BARISTA')")
public class BaristaController {

    final UserRepo userRepo;

    Iterable<User> users;

    public BaristaController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findByUserNumber(filter);
        } else {
            users = userRepo.findAll();
        }

        model.addAttribute("users", users);
        model.addAttribute("filter", filter);

        return "baristapage";
    }

    @GetMapping("add_bonus/{user}")
    public String addBonus(@RequestParam(required = false, defaultValue = "") String filter,
                           @PathVariable User user,
                           Model model) {
        user.addCoffee();

        userRepo.save(user);

        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findByUserNumber(filter);
        } else {
            users = userRepo.findAll();
        }

        model.addAttribute("users", users);
        model.addAttribute("filter", filter);

        return "baristapage";
    }

    @GetMapping("del_bonus/{user}")
    public String delBonus(@RequestParam(required = false, defaultValue = "") String filter,
                           @PathVariable User user,
                           Model model) {
        user.delHappyCoffee();

        userRepo.save(user);

        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findByUserNumber(filter);
        } else {
            users = userRepo.findAll();
        }

        model.addAttribute("users", users);
        model.addAttribute("filter", filter);

        return "baristapage";
    }
}