package com.example.coffeapp.telegram;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
public class MyCoffeeBot extends TelegramLongPollingBot {

    @Autowired
    BotService botService;

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
                        execute(SendMessage.builder()
                                .text(botService.getStats())
                                .chatId(message.getChatId().toString())
                                .build());
                        return;
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "raft_coffee_bot";
    }

    @Override
    public String getBotToken() {
        return "5168296742:AAH-tXdkEqGfyI145vAhIRm5PoSKQGJqPxA";
    }

}
