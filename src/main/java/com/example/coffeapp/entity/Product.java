package com.example.coffeapp.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;
    private int averagePrice;
    private int middlePrice;
    private int bigPrice;
}
