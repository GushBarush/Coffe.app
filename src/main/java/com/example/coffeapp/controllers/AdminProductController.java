package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.Product;
import com.example.coffeapp.repository.ProductRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/product")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminProductController {

    final ProductRepo productRepo;

    Iterable<Product> products;

    public AdminProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public String product(Model model) {
        products = productRepo.findAll();

        model.addAttribute("products", products);

        return "product";
    }

    @GetMapping("/new")
    public String productNew() {
        return "productNew";
    }

    @PostMapping("/new")
    public String productAddNew(Product product, Model model) {

        if (product.getProductName() == null || product.getProductName().equals("")) {
            model.addAttribute("message", "Название не может быть пустым");

            return "productNew";
        }

        productRepo.save(product);

        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{product}")
    public String productDelete(@PathVariable Product product) {
        productRepo.delete(product);

        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{product}")
    public String productEditForm(@PathVariable Product product, Model model){
        model.addAttribute("product", product);

        return "productEdit";
    }

    @PostMapping("/edit")
    public String productEdit(@RequestParam String productName,
                              @RequestParam String averagePrice,
                              @RequestParam String middlePrice,
                              @RequestParam String bigPrice,
                              @RequestParam("productId") Product product,
                              Model model){

        if (productName == null || productName.equals("")) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Название не может быть пустым");

            return "productEdit";
        }
        if (middlePrice == null || middlePrice.equals("")) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Не может быть пустым");

            return "productEdit";
        }
        if (averagePrice == null || averagePrice.equals("")) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Не может быть пустым");

            return "productEdit";
        }
        if (bigPrice == null || bigPrice.equals("")) {
            model.addAttribute("product", product);
            model.addAttribute("message", "Не может быть пустым");

            return "productEdit";
        }

        product.setProductName(productName);
        product.setAveragePrice(Integer.parseInt(averagePrice));
        product.setMiddlePrice(Integer.parseInt(middlePrice));
        product.setBigPrice(Integer.parseInt(bigPrice));

        productRepo.save(product);

        return "redirect:/admin/product";
    }
}
