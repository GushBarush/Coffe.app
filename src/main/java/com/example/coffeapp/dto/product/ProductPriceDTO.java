package com.example.coffeapp.dto.product;

import com.example.coffeapp.entity.product.Product;
import com.example.coffeapp.entity.product.ProductSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductPriceDTO implements Serializable {

    private Long id;

    private Double price;

    private ProductSize productSize;

    private Product product;
}
