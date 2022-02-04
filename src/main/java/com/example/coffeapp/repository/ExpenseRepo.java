package com.example.coffeapp.repository;

import com.example.coffeapp.entity.order.Expense;
import com.example.coffeapp.entity.payday.PayDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    List<Expense> findAllByPayDay(PayDay payDay);
}
