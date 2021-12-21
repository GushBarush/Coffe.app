package com.example.coffeapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private String productName;
    private int averagePrice;
    private int middlePrice;
    private int bigPrice;

}
