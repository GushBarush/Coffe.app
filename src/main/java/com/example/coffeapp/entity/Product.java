package com.example.coffeapp.entity;

import org.springframework.beans.factory.annotation.Value;
import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;
    @Value("0")
    private int averagePrice;
    @Value("0")
    private int middlePrice;
    @Value("0")
    private int bigPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(int averagePrice) {
        this.averagePrice = averagePrice;
    }

    public int getMiddlePrice() {
        return middlePrice;
    }

    public void setMiddlePrice(int middlePrice) {
        this.middlePrice = middlePrice;
    }

    public int getBigPrice() {
        return bigPrice;
    }

    public void setBigPrice(int bigPrice) {
        this.bigPrice = bigPrice;
    }
}
