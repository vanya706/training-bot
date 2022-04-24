package com.ivan.mostovyi.bot;

import com.ivan.mostovyi.bot.handler.IncomingMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrainingBot extends TelegramLongPollingBot {

  @Value("${bot.name}")
  private String botName;

  @Value("${bot.token}")
  private String botToken;

  private final IncomingMessageHandler incomingMessageHandler;

  @Override
  public void onUpdateReceived(Update update) {
    try {
      if (update.hasMessage()) {
        Message message = update.getMessage();
        if (message.hasText() || message.hasLocation()) {
          List<SendMessage> outcomeMessages = incomingMessageHandler.handleIncomingMessage(message);
          for (SendMessage outcomeMessage : outcomeMessages) {
            execute(outcomeMessage);
          }
        }
      }
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
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
