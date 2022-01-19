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
    public String productNew(@RequestParam(name = "dop") Boolean dop) {
        if(dop) {
            return "dopNew";
        }

        return "productNew";
    }

    @PostMapping("/new_dop")
    public String saveNewDopProduct(@RequestParam(name = "productName") String productName,
                                 @RequestParam(name = "price") Double price,
                                 @RequestParam(name = "description") String description) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setDop(true);
        productDTO.setCategory("dop");
        productDTO.setProductName(productName);
        productDTO.setDescription(description);

        productService.saveNewDopProduct(productDTO, price);

        return "redirect:/admin/product";
    }

    @PostMapping("/new")
    public String saveNewDopProduct(@RequestParam(name = "productName") String productName,
                                    @RequestParam(name = "priceSmall") Double priceSmall,
                                    @RequestParam(name = "priceMiddle") Double priceMiddle,
                                    @RequestParam(name = "priceBig") Double priceBig,
                                    @RequestParam(name = "category") String category,
                                    @RequestParam(name = "description") String description) {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setDop(false);
        productDTO.setCategory(category);
        productDTO.setProductName(productName);
        productDTO.setDescription(description);

        productService.saveNewProduct(productDTO, priceSmall, priceMiddle, priceBig);

        return "redirect:/admin/product";
    }

    @PostMapping("/delete")
    public String productDelete(@RequestParam(name = "productId") Long productId) {

        productService.productDelete(productId);

        return "redirect:/admin/product";
    }
}
