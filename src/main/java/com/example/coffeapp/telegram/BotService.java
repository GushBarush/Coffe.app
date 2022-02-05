package com.example.coffeapp.telegram;

import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BotService {

    final PayDayRepo payDayRepo;

    public String getStats() {
        String stats = "Смена закрыта";

        PayDay payDay = payDayRepo.findByActive(true);

        if (payDay != null){
            stats = "Cмену открыл: " + payDay.getUser().getName() + ". " +
                    "В " + payDay.getOpenTime().getHour() + ":" + payDay.getOpenTime().getMinute() + ". " +
                    "Всего: " + payDay.getSumAll() + ". " +
                    "Расходы: " + payDay.getSumExpense() + ". " +
                    "Наличные: " + payDay.getSumCash() + ". " +
                    "Безнал: " + payDay.getSumNotCash() + ". " +
                    "Бесплатных: " + payDay.getSumFree() + ".";
        }

        return stats;
    }
}
