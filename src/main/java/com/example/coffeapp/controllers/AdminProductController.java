package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.product.*;
import com.example.coffeapp.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/product")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminProductController {

    final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String product(Model model) {
        model.addAttribute("products", productService.allProduct());

        return "product";
    }

    @GetMapping("/new")
    public String productNew() {
        return "dopNew";
    }

    @PostMapping("/new")
    public String saveNewProduct(@RequestParam(name = "productDTO") ProductDTO productDTO,
                                 @RequestParam(name = "productPriceDTO") ProductPriceDTO productPriseDTO,
                                 @RequestParam boolean dop) {

        productService.saveNewProduct(productPriseDTO, productDTO, dop);

        return "redirect:/product";
    }
}
