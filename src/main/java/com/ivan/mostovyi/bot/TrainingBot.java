package com.ivan.mostovyi.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TrainingBot extends TelegramLongPollingBot {

  @Value("${bot.name}")
  private String botName;

  @Value("${bot.token}")
  private String botToken;

  @Override
  public void onUpdateReceived(Update update) {
    // ToDo implements
  }

  @Override
  public String getBotUsername() {
    return botName;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

}
