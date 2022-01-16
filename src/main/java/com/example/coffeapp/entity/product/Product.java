package com.example.coffeapp.entity.product;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "img_png")
    private byte[] imgPng;

    @Column(name = "is_dop", nullable = false)
    private boolean isDop;
}
