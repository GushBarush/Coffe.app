package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.product.*;
import com.example.coffeapp.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String productNew(@RequestParam boolean dop, Model model) {
        List<Object> productInfo = productService.getNewInfo(dop); // Array DTO

        model.addAttribute("productDTO", productInfo.get(0)); // ProductDTO
        model.addAttribute("productPriceDTO", productInfo.get(1)); // ProductPriseDTO

        if(dop) {
            return "dopNew";
        }

        return "productNew";
    }

    @PostMapping("/new")
    public String saveNewProduct(@PathVariable ProductDTO productDTO,
                                 @PathVariable ProductPriceDTO productPriseDTO) {

        productService.saveNewProduct(productPriseDTO, productDTO);

        return "redirect:/product";
    }
}
