package com.example.coffeapp;

import com.example.coffeapp.repository.PayDayRepo;
import com.example.coffeapp.telegram.BotService;
import com.example.coffeapp.telegram.MyCoffeeBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MyCoffeeBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
