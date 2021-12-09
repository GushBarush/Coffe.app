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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/barista")
@PreAuthorize("hasAuthority('BARISTA')")
public class BaristaController {
    @Autowired
    UserRepo userRepo;

    Iterable<User> users;

    @GetMapping
    public String userList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        if (filter != null && !filter.isEmpty()) {
            users = userRepo.findByUserNumber(filter);
        } else {
            filter = "0000";
            users = userRepo.findByUserNumber(filter);
        }

        model.addAttribute("users", users);
        model.addAttribute("filter", filter);

        return "baristapage";
    }

    @GetMapping("add_bonus/{user}")
    public String addBonus(@PathVariable User user,
                           @RequestParam(required = false, defaultValue = "") String filter,
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
    public String delBonus(@PathVariable User user,
                           @RequestParam(required = false, defaultValue = "") String filter,
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
