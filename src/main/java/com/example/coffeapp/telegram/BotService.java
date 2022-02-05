package com.example.coffeapp.telegram;

import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BotService {

    static PayDayRepo payDayRepo;

    public static String getStats() {
        String stats = "Смена закрыта";

        PayDay payDay = payDayRepo.findByActive(true);

        if (payDay != null){
            stats = "Cмену открыл: " + payDay.getUser().getName() + ".\n" +
                    "В " + payDay.getOpenTime().getHour() + ":" + payDay.getOpenTime().getMinute() + ".\n" +
                    "Всего: " + payDay.getSumAll() + ".\n" +
                    "Расходы: " + payDay.getSumExpense() + ".\n" +
                    "Наличные: " + payDay.getSumCash() + ".\n" +
                    "Безнал: " + payDay.getSumNotCash() + ".\n" +
                    "Бесплатных: " + payDay.getSumFree() + ".";
        }

        return stats;
    }
}
