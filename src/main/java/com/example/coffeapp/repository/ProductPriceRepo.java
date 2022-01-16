package com.example.coffeapp.repository;

import com.example.coffeapp.entity.product.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepo extends JpaRepository<ProductPrice, Long> {

}
