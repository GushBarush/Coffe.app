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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order", schema = "public")
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
    private LocalDateTime time;

    @Column(name = "sum")
    private double sum;

    @Column(name = "is_cash")
    private boolean isCash;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pay_day_id", nullable = false)
    private PayDay payDay;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_price_id"))
    private List<ProductPrice> productPriceList = new ArrayList<>();

    public void setPrice(ProductPrice productPrice) {
        productPriceList.add(productPrice);
    }
}