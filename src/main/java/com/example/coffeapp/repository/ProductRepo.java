package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllActiveTrueAndByCategory(String category);

    List<Product> findAllByActiveTrueAndCategoryIsNot(String category);

    List<Product> findAllByActiveTrue();
}
