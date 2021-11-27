package com.example.coffeapp.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    @Value("0")
    private Long averagePrice;
    @Value("0")
    private Long middlePrice;
    @Value("0")
    private Long bigPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Long getMiddlePrice() {
        return middlePrice;
    }

    public void setMiddlePrice(Long middlePrice) {
        this.middlePrice = middlePrice;
    }

    public Long getBigPrice() {
        return bigPrice;
    }

    public void setBigPrice(Long bigPrice) {
        this.bigPrice = bigPrice;
    }
}
