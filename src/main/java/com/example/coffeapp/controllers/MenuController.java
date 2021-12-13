package com.example.coffeapp.controllers;

import com.example.coffeapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    final ProductService productService;

    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String product(Model model) {

        model.addAttribute("products", productService.allProduct());

        return "menu";
    }
}
