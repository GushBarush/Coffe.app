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
    public String saveNewDopProduct(@RequestParam(name = "productName") String productName,
                                 @RequestParam(name = "price") Double price,
                                 @RequestParam(name = "description") String description,
                                    @RequestParam(name = "file") MultipartFile file) throws IOException {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setDop(true);
        productDTO.setCategory("dop");
        productDTO.setProductName(productName);
        productDTO.setDescription(description);

        productService.saveNewDopProduct(productDTO, price, file);

        return "redirect:/admin/product";
    }

    @PostMapping("/new")
    public String saveNewProduct(@RequestParam(name = "productName") String productName,
                                    @RequestParam(name = "priceSmall") Double priceSmall,
                                    @RequestParam(name = "priceMiddle") Double priceMiddle,
                                    @RequestParam(name = "priceBig") Double priceBig,
                                    @RequestParam(name = "category") String category,
                                    @RequestParam(name = "description") String description,
                                    @RequestParam(name = "file") MultipartFile file) throws IOException {

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
                                @RequestParam(name = "productName") String productName,
                                @RequestParam(name = "price") Double price,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {

        productService.updateDopProduct(productId, productPriceId, productName, price, description, file);

        return "redirect:/admin/product";
    }

    @PostMapping("/edit")
    public String productUpdate(@RequestParam(name = "productId") Long productId,
                                @RequestParam(name = "category") String category,
                                @RequestParam(name = "productName") String productName,
                                @RequestParam(name = "smallPrice") Double priceSmall,
                                @RequestParam(name = "mediumPrice") Double priceMiddle,
                                @RequestParam(name = "bigPrice") Double priceBig,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "file", required = false) MultipartFile file, Model model) throws IOException {

        productService.updateProduct(productId, productName, priceSmall, priceMiddle, priceBig, category, description, file);

        return "redirect:/admin/product";
    }
}
