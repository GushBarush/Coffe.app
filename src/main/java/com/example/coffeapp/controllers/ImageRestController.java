package com.example.coffeapp.controllers;

import com.example.coffeapp.entity.product.ProductImage;
import com.example.coffeapp.repository.ProductImageRepo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
public class ImageRestController {

    final ProductImageRepo productImageRepo;

    public ImageRestController(ProductImageRepo productImageRepo) {
        this.productImageRepo = productImageRepo;
    }

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        ProductImage productImage = productImageRepo.findById(id).orElse(null);

        return ResponseEntity.ok()
                .header("fileName", productImage.getOriginalFileName())
                .contentType(MediaType.valueOf(productImage.getContentType()))
                .contentLength(productImage.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(productImage.getBytes())));
    }
}
