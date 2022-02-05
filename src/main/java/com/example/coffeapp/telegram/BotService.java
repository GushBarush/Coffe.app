package com.example.coffeapp.telegram;

import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BotService {

    @Autowired
    PayDayRepo payDayRepo;

    public PayDay getStats() {
        return payDayRepo.findByActive(true);
    }
}
