package com.example.coffeapp.repository;

import com.example.coffeapp.entity.order.Order;
import com.example.coffeapp.entity.payday.PayDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> findAllByPayDay(PayDay payDay);
}
