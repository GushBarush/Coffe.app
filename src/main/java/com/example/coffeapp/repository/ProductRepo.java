package com.example.coffeapp.repository;

import com.example.coffeapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);
}
