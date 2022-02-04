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
    public String productNew(@RequestParam(name = "dop") Boolean dop) {
        if(dop) {
            return "dopNew";
        }

        return "productNew";
    }

    @PostMapping("/new_dop")
    public String saveNewDopProduct(@RequestParam(name = "productName", defaultValue = "") String productName,
                                 @RequestParam(name = "price", defaultValue = "0.0") Double price,
                                 @RequestParam(name = "description") String description,
                                    @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {

        if(productName.equals("")) {
            model.addAttribute("messageName", "Не указанно назвине");
            return "dopNew";
        }
        if(price == 0.0) {
            model.addAttribute("messagePrice", "Не указанна цена");
            return "dopNew";
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setDop(true);
        productDTO.setCategory("dop");
        productDTO.setProductName(productName);
        productDTO.setDescription(description);

        productService.saveNewDopProduct(productDTO, price, file);

        return "redirect:/admin/product";
    }

    @PostMapping("/new")
    public String saveNewProduct(@RequestParam(name = "productName", defaultValue = "") String productName,
                                    @RequestParam(name = "priceSmall", defaultValue = "0.0") Double priceSmall,
                                    @RequestParam(name = "priceMiddle", defaultValue = "0.0") Double priceMiddle,
                                    @RequestParam(name = "priceBig", defaultValue = "0.0") Double priceBig,
                                    @RequestParam(name = "category") String category,
                                    @RequestParam(name = "description", defaultValue = "") String description,
                                    @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {

        if(productName.equals("")) {
            model.addAttribute("messageName", "Не указанно назвине");
            return "productNew";
        }
        if(priceSmall == 0.0) {
            model.addAttribute("messageSmall", "Не указанна цена");
            return "productNew";
        }
        if(priceMiddle == 0.0) {
            model.addAttribute("messageMiddle", "Не указанна цена");
            return "productNew";
        }
        if(priceBig == 0.0) {
            model.addAttribute("messageBig", "Не указанна цена");
            return "productNew";
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setDop(false);
        productDTO.setCategory(category);
        productDTO.setProductName(productName);
        productDTO.setDescription(description);

        productService.saveNewProduct(productDTO, priceSmall, priceMiddle, priceBig, file);

        return "redirect:/admin/product";
    }

    @PostMapping("/delete")
    public String productDelete(@RequestParam(name = "productId") Long productId) {

        productService.productDelete(productId);

        return "redirect:/admin/product";
    }

    @GetMapping("/edit")
    public String getProductInfo(@RequestParam(name = "productId") Long productId, Model model) {
        ProductDTO productDTO = productService.getProductDTO(productId);

        model.addAttribute("productDTO", productDTO);

        if (productDTO.isDop()) {
            model.addAttribute("productPriceDTO", productService.getProductPriceDTO(productId));
            return "dopEdit";
        } else {
            model.addAttribute("productPriceView", productService.getProductPriceView(productId));
        }

        return "productEdit";
    }

    @PostMapping("/edit_dop")
    public String productDopUpdate(@RequestParam(name = "productId") Long productId,
                                @RequestParam(name = "productPriceId") Long productPriceId,
                                @RequestParam(name = "productName", defaultValue = "") String productName,
                                @RequestParam(name = "price", defaultValue = "0.0") Double price,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {

        if(productName.equals("")) {
            model.addAttribute("messageName", "Не указанно назвине");
            return "dopEdit";
        }
        if(price == 0.0) {
            model.addAttribute("messagePrice", "Не указанна цена");
            return "dopEdit";
        }

        productService.updateDopProduct(productId, productPriceId, productName, price, description, file);

        return "redirect:/admin/product";
    }

    @PostMapping("/edit")
    public String productUpdate(@RequestParam(name = "productId") Long productId,
                                @RequestParam(name = "category") String category,
                                @RequestParam(name = "productName", defaultValue = "") String productName,
                                @RequestParam(name = "smallPrice", defaultValue = "0.0") Double priceSmall,
                                @RequestParam(name = "mediumPrice", defaultValue = "0.0") Double priceMiddle,
                                @RequestParam(name = "bigPrice", defaultValue = "0.0") Double priceBig,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "file", required = false) MultipartFile file, Model model) throws IOException {

        if(productName.equals("")) {
            model.addAttribute("messageName", "Не указанно назвине");
            return "productEdit";
        }
        if(priceSmall == 0.0) {
            model.addAttribute("messageSmall", "Не указанна цена");
            return "productEdit";
        }
        if(priceMiddle == 0.0) {
            model.addAttribute("messageMiddle", "Не указанна цена");
            return "productEdit";
        }
        if(priceBig == 0.0) {
            model.addAttribute("messageBig", "Не указанна цена");
            return "productEdit";
        }

        productService.updateProduct(productId, productName, priceSmall, priceMiddle, priceBig, category, description, file);

        return "redirect:/admin/product";
    }
}
