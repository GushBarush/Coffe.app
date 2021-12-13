package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.User;
import com.example.coffeapp.service.UserService;
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

    final UserService userService;

    public BaristaController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(@RequestParam(required = false, defaultValue = "") String filter, Model model) {

        model.addAttribute("users", userService.userFilterNumber(filter));
        model.addAttribute("filter", filter);

        return "baristapage";
    }

    @GetMapping("add_bonus/{user}")
    public String addBonus(@RequestParam(required = false, defaultValue = "") String filter,
                           @PathVariable User user,
                           Model model) {
        userService.addCoffe(user);

        model.addAttribute("users", userService.userFilterNumber(filter));
        model.addAttribute("filter", filter);

        return "baristapage";
    }

    @GetMapping("del_bonus/{user}")
    public String delBonus(@RequestParam(required = false, defaultValue = "") String filter,
                           @PathVariable User user,
                           Model model) {

        userService.delHappyCoffe(user);

        model.addAttribute("users", userService.userFilterNumber(filter));
        model.addAttribute("filter", filter);

        return "baristapage";
    }
}