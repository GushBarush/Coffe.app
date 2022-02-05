package com.example.coffeapp.telegram;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
public class MyCoffeeBot extends TelegramLongPollingBot {

    @Autowired
    PayDayRepo payDayRepo;

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    @SneakyThrows
    public void handleMessage(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if(commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/hello":
                        execute(SendMessage.builder()
                                    .text("Привет")
                                    .chatId(message.getChatId().toString())
                                    .build());
                        return;
                    case "/smena":
                        smena(message);
                        return;
                    case "/smena@raft_coffee_bot":
                        smena(message);
                }
            }
        }
    }

    @SneakyThrows
    public void smena(Message message) {
        execute(SendMessage.builder()
                .text(parse())
                .chatId(message.getChatId().toString())
                .build());
    }

    @SneakyThrows
    public void openPayDay(PayDayDTO payDayDTO) {
        PayDay payDay = payDayRepo.getById(payDayDTO.getId());

        execute(SendMessage.builder()
                .text(
                        payDay.getUser().getName() + " открыл смену.\n" +
                                "в: " + payDay.getOpenTime().getHour() +
                                ":" + payDay.getOpenTime().getMinute())
                .chatId("-1001361341198")
                .build());
    }

    @SneakyThrows
    public void endPayDay(PayDay payDay) {

        execute(SendMessage.builder()
                .text(
                        payDay.getUser().getName() + " закрыл смену.\n" +
                                "в: " + payDay.getCloseTime().getHour() +
                                ":" + payDay.getCloseTime().getMinute() + "\n" +
                                "Всего: " + payDay.getSumAll() + "\n" +
                                "Расходы: " + payDay.getSumExpense() + "\n" +
                                "Наличные: " + payDay.getSumCash() + "\n" +
                                "Безнал: " + payDay.getSumNotCash() + "\n" +
                                "Бесплатных: " + payDay.getSumFree())
                .chatId("-1001361341198")
                .build());
    }

    @Override
    public String getBotUsername() {
        return "raft_coffee_bot";
    }

    @Override
    public String getBotToken() {
        return "5168296742:AAH-tXdkEqGfyI145vAhIRm5PoSKQGJqPxA";
    }

    public String parse() {
        PayDay payDay = payDayRepo.findByActive(true);
        String stats = "Смена закрыта";

        if (payDay != null){
            return  "Cмену открыл: " + payDay.getUser().getName() + "\n" +
                    "В " + payDay.getOpenTime().getHour() + ":" + payDay.getOpenTime().getMinute() + "\n" +
                    "Всего: " + payDay.getSumAll() + "\n" +
                    "Расходы: " + payDay.getSumExpense() + "\n" +
                    "Наличные: " + payDay.getSumCash() + "\n" +
                    "Безнал: " + payDay.getSumNotCash() + "\n" +
                    "Бесплатных: " + payDay.getSumFree();
        }

        return stats;
    }

}
