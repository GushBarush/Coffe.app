package com.example.coffeapp.repository;

import com.example.coffeapp.entity.payday.PayDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayDayRepo extends JpaRepository<PayDay, Long> {

    PayDay findByActive(Boolean b);
}
