package com.example.coffeapp.controllers;

import com.example.coffeapp.dto.product.*;
import com.example.coffeapp.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public String productNew(@RequestParam(name = "dop") Boolean dop, Model model) {

        model.addAttribute("dop", dop);

        return "productNew";
    }

    @PostMapping("/new")
    public String saveNewProduct(@RequestParam(name = "productName") String productName,
                                 @RequestParam(name = "description") String description,
                                 @RequestParam(name = "category") String category,
                                 @RequestParam(name = "dop") Boolean isDop,
                                 @RequestParam(name = "priceSmall") Double priceSmall,
                                 @RequestParam(name = "priceMedium", required = false) Double priceMedium,
                                 @RequestParam(name = "priceBig", required = false) Double priceBig,
                                 @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {

        if (isDop) {
            productService.saveNewDopProduct(productName, description, category,
                    priceSmall, file);
        } else {
            productService.saveNewProduct(productName, description, category,
                    priceSmall, priceMedium, priceBig, file);
        }

        return "redirect:/admin/product";
    }

    @PostMapping("/delete")
    public String productDelete(@RequestParam(name = "productId") Long productId) {

        productService.productDelete(productId);

        return "redirect:/admin/product";
    }

    @GetMapping("/edit")
    public String getProductInfo(@RequestParam(name = "productId") Long productId, Model model) {
        ProductView productView = productService.getProductView(productId);

        model.addAttribute("productDTO", productView);

        return "productEdit";
    }

    @PostMapping("/edit")
    public String productUpdate(@RequestParam(name = "productId") Long productId,
                                @RequestParam(name = "category") String category,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "productName") String productName,
                                @RequestParam(name = "priceSmall") Double priceSmall,
                                @RequestParam(name = "priceMedium", required = false, defaultValue = "0.0") Double priceMedium,
                                @RequestParam(name = "priceBig", required = false, defaultValue = "0.0") Double priceBig,
                                @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {

            productService.upgradeProduct(productId, category, description,
                    productName, priceSmall, priceMedium, priceBig, file);

        return "redirect:/admin/product";
    }
}
