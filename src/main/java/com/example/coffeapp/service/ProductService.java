package com.example.coffeapp.service;

import com.example.coffeapp.entity.product.Product;
import com.example.coffeapp.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;

    public List<Product> allProduct(){
        return productRepo.findAll();
    }

    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    public void delProduct(Product product) {
        productRepo.delete(product);
    }

    public void updateProduct(Product product, String newName, int averagePrice, int middlePrice, int bigPrice) {
//
//        product.setProductName(newName);
//        product.setAveragePrice(averagePrice);
//        product.setMiddlePrice(middlePrice);
//        product.setBigPrice(bigPrice);
//
//        saveProduct(product);
    }

    public void newProduct(String productName, int averagePrice, int middlePrice, int bigPrice) {
//        Product product = new Product();
//
//        product.setProductName(productName);
//        product.setAveragePrice(averagePrice);
//        product.setMiddlePrice(middlePrice);
//        product.setBigPrice(bigPrice);
//
//        saveProduct(product);
    }
}
