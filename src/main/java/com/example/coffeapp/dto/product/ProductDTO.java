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
public class ProductDTO implements Serializable {

    private Long id;

    private String category;

    private String productName;

    private String description;

    private Boolean active;

    private String imgPng;

    private boolean isDop;
}
