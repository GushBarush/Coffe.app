package com.example.coffeapp.dto.order;

import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.entity.product.ProductPrice;
import com.example.coffeapp.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDTO implements Serializable {

    private Long id;

    private LocalDateTime time;

    private double sum;

    private boolean isCash;

    private boolean isActive;

    private User user;

    private PayDay payDay;

    private List<ProductPrice> productPrice;
}
