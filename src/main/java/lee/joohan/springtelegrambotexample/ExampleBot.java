package lee.joohan.springtelegrambotexample;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Created by Joohan Lee on 2019-09-29
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ExampleBot extends TelegramLongPollingBot {
  @Value("${telegram.bot.name}")
  private String name;
  @Value("${telegram.bot.key}")
  private String key;

  private final BotService botService;

  @Override
  public void onUpdateReceived(Update update) {
    //  Useful for Debugging https://api.telegram.org/bot{token}/getUpdates
    Message message = Optional.ofNullable(update.getMessage()).get();

    System.out.println("The received message: " + message);

    reply(message.getChatId(), message.getText());
  }

  @Override
  public String getBotUsername() {
    return name;
  }

  @Override
  public String getBotToken() {
    return key;
  }

  private boolean reply(long chatId, String message) {
    SendMessage sendMessageRequest = new SendMessage();
    sendMessageRequest.setChatId(chatId);
    sendMessageRequest.setText(message);

    try {
      execute(sendMessageRequest);
      return true;
    } catch (TelegramApiException e) {
      log.error("Failed to reply to the chat group [chatId: {} , message: {}]", chatId, message, e);
    }
    return false;
  }
}

