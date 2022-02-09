package com.example.coffeapp.entity.product;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product", schema = "public")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "is_dop", nullable = false)
    private boolean isDop;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProductImage productImage;
}
