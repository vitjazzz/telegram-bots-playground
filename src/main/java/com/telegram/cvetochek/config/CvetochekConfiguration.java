package com.telegram.cvetochek.config;

import com.telegram.cvetochek.bot.CvetochekBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class CvetochekConfiguration {

    @Autowired
    private TelegramLongPollingBot telegramBot;

    @Autowired
    private ComplimentsDictionary complimentsDictionary;

    static {
        ApiContextInitializer.init();
    }
    @PostConstruct
    public void start() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiRequestException e) {
            log.error("Failed to register bot!", e);
        }

        System.out.println(complimentsDictionary.getDictionary());
    }
}
