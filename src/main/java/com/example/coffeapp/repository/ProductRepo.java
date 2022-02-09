package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByActiveAndCategory(Boolean b, String category);

    List<Product> findAllByActiveAndCategoryIsNot(Boolean b, String category);

    List<Product> findAllByActive(Boolean b);
}
