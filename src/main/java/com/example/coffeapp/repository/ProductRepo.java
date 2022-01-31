package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(String category);

    List<Product> findAllByDopIsFalse();
    List<Product> findAllByDopIsTrue();
}
