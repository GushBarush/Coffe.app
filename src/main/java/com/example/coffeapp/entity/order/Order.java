package com.example.coffeapp.entity.order;

import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.entity.product.ProductPrice;
import com.example.coffeapp.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time", nullable = false)
    private Timestamp time;

    @Column(name = "sum")
    private double sum;

    @Column(name = "is_cash", nullable = false)
    private boolean isCash;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pay_day_id")
    private PayDay payDay;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_price_id"))
    private List<ProductPrice> productPrice;
}
