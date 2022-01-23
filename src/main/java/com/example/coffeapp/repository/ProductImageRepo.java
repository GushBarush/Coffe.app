package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
}
