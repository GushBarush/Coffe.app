package com.example.coffeapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_price", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private ProductSize productSize;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
