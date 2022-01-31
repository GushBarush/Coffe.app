package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.user.User;
import com.example.coffeapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasAuthority('BARISTA')")
public class OrderController {

    final UserService userService;

    public OrderController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String newOrder(@RequestParam(name = "id") Long payDayId,
                           @RequestParam(name = "filter", required = false, defaultValue = "") String filter, Model model) {

        model.addAttribute("PayDayId", payDayId);
        model.addAttribute("users", userService.userFilterNumber(filter));
        model.addAttribute("filter", filter);

        return "newOrder";
    }

    @GetMapping("{user}")
    public String selectUser(@PathVariable User user,
                             @RequestParam(name = "id") Long payDayId, Model model) {
        return "orderForm";
    }
}
