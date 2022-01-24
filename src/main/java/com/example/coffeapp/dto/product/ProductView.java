package com.example.coffeapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductView {

    private Long id;

    private String category;

    private String productName;

    private String description;

    private Double priceSmall;

    private Double priceMedium;

    private Double priceBig;

    private Long imageId;
}
