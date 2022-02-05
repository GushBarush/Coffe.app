package com.example.coffeapp.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class MyCoffeeBot extends AbilityBot {

    public static String BOT_TOKEN = "5168296742:AAH-tXdkEqGfyI145vAhIRm5PoSKQGJqPxA";
    public static String BOT_USERNAME = "raft_coffee_bot";

    @Autowired
    BotService botService;

    public MyCoffeeBot() {
        super(BOT_TOKEN, BOT_USERNAME);
    }

    @Override
    public long creatorId() {
        return 1223583252;
    }

    public Ability sayHelloWorld() {
        return Ability
                .builder()
                .name("hello")
                .info("says hello world!")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.send("Hello world!", ctx.chatId()))
                .build();
    }

    public Ability saySmena() {
        return Ability
                .builder()
                .name("smena")
                .info("Дай информацию о текущей смене")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> silent.send(botService.getStats(), ctx.chatId()))
                .build();
    }
}
