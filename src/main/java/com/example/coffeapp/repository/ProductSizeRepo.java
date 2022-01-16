package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepo extends JpaRepository<ProductSize, Long> {

    ProductSize findBySizeName(String s);
}
