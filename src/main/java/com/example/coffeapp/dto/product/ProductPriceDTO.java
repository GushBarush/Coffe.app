package com.example.coffeapp.dto.product;

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

    private ProductSizeDTO productSize;

    private ProductDTO product;
}
