package com.example.coffeapp.controllers;

import com.example.coffeapp.service.OrderService;
import com.example.coffeapp.service.ProductService;
import com.example.coffeapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@PreAuthorize("hasAuthority('BARISTA')")
public class OrderController {

    final UserService userService;
    final OrderService orderService;
    final ProductService productService;

    public OrderController(UserService userService, OrderService orderService, ProductService productService) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String newOrder(@RequestParam(name = "id") Long payDayId,
                           @RequestParam(name = "filter", required = false, defaultValue = "") String filter, Model model) {

        model.addAttribute("PayDayId", payDayId);
        model.addAttribute("users", userService.userFilterNumber(filter));
        model.addAttribute("filter", filter);

        return "newOrder";
    }

    @PostMapping
    public String selectUser(@RequestParam(name = "userId") Long userId,
                             @RequestParam(name = "payDayId") Long payDayId, Model model) {

        model.addAttribute("order", orderService.newOrder(userId, payDayId));
        model.addAttribute("products", productService.allProduct(false));
        model.addAttribute("productsDop", productService.allProduct(true));

        return "orderForm";
    }
}
