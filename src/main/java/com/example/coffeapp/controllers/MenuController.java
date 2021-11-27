package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.Product;
import com.example.coffeapp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    ProductRepo productRepo;

    Iterable<Product> products;

    @GetMapping
    public String product(Model model) {
        products = productRepo.findAll();

        model.addAttribute("products", products);

        return "menu";
    }
}
