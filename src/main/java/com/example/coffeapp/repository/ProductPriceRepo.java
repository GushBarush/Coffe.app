package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.Product;
import com.example.coffeapp.entity.product.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPriceRepo extends JpaRepository<ProductPrice, Long> {

    List<ProductPrice> findAllByProduct(Product product);

    ProductPrice findByProduct(Product product);
}
