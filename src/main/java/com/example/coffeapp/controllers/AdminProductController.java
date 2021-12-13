package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.Product;
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
        return "productNew";
    }

    @PostMapping("/new")
    public String productAddNew(@RequestParam String productName,
                                @RequestParam(defaultValue = "0") int averagePrice,
                                @RequestParam(defaultValue = "0") int middlePrice,
                                @RequestParam(defaultValue = "0") int bigPrice, Model model) {

        if (productName == null || productName.equals("")) {
            model.addAttribute("messageName", "Название не может быть пустым");
            return "productNew";
        }

        productService.newProduct(productName, averagePrice, middlePrice, bigPrice);

        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{product}")
    public String productDelete(@PathVariable Product product) {
        productService.delProduct(product);

        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{product}")
    public String productEditForm(@PathVariable Product product, Model model){
        model.addAttribute("product", product);

        return "productEdit";
    }

    @PostMapping("/edit")
    public String productEdit(@RequestParam String productName,
                              @RequestParam(defaultValue = "0") int averagePrice,
                              @RequestParam(defaultValue = "0") int middlePrice,
                              @RequestParam(defaultValue = "0") int bigPrice,
                              @RequestParam("productId") Product product,
                              Model model){

        if (productName == null || productName.equals("")) {
            model.addAttribute("product", product);
            model.addAttribute("messageName", "Название не может быть пустым");
            return "productEdit";
        }

        productService.updateProduct(product, productName, averagePrice, middlePrice, bigPrice);

        return "redirect:/admin/product";
    }
}
