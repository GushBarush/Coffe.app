package com.example.coffeapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "size", schema = "public")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "size_name", nullable = false)
    private String sizeName;
}
