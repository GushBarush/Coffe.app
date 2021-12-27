package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
