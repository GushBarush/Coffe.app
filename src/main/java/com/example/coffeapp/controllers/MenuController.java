package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.Product;
import com.example.coffeapp.repository.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

    final ProductRepo productRepo;

    Iterable<Product> products;

    public MenuController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public String product(Model model) {
        products = productRepo.findAll();

        model.addAttribute("products", products);

        return "menu";
    }
}
