package com.ivan.mostovyi.bot.handler;

import com.ivan.mostovyi.constant.UserState;
import com.ivan.mostovyi.helper.ResourceBundleHelper;
import com.ivan.mostovyi.service.UserChatHistoryService;
import com.ivan.mostovyi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class IncomingMessageHandler {

    private final UserChatHistoryService userChatHistoryService;

    private final ResourceBundleHelper resourceBundleHelper;

    private final UserService userService;


    public Optional<SendMessage> handleIncomingMessage(Message message) {
        UserState userState = userService.getUserState(message.getFrom().getId(), message.getChatId());

        if (message.getText().equals(getRunCommand(message))) {
            return onRunChosen(message);
        }
        if (message.getText().equals(getBarsCommand(message))) {
            return onBarsChosen(message);
        }
        if (message.getText().equals(getHorizontalBarCommand(message))) {
            return onHorizontalBarChosen(message);
        }

        switch (userState) {
            case START:
            case HELP:
                return onMenuChosen(message);
            case HORIZONTAL_BAR:
            case BARS:
            case RUN:
                addUserChatMessageToHistory(message, userState);
                break;
        }
        return Optional.of(defaultSuccessResponseMessage(message));
    }

    private void addUserChatMessageToHistory(Message message, UserState userState) {
        userChatHistoryService.create(message, userState);
    }


    private Optional<SendMessage> onHorizontalBarChosen(Message message) {
        userService.setUserState(message, UserState.HORIZONTAL_BAR);
        return Optional.of(defaultSuccessResponseMessage(message));
    }

    private Optional<SendMessage> onBarsChosen(Message message) {
        userService.setUserState(message, UserState.BARS);
        return Optional.of(defaultSuccessResponseMessage(message));
    }

    private Optional<SendMessage> onRunChosen(Message message) {
        userService.setUserState(message, UserState.RUN);
        return Optional.of(defaultSuccessResponseMessage(message));
    }

    private String getRunCommand(Message message) {
        return resourceBundleHelper.getLocalizedString("run", message.getFrom().getLanguageCode());
    }

    private String getHorizontalBarCommand(Message message) {
        return resourceBundleHelper.getLocalizedString("horizontalBar", message.getFrom().getLanguageCode());
    }

    private String getBarsCommand(Message message) {
        return resourceBundleHelper.getLocalizedString("bars", message.getFrom().getLanguageCode());
    }

    private Optional<SendMessage> onMenuChosen(Message message) {
        return Optional.of(new SendMessage() {{
            enableMarkdown(true);
            setReplyMarkup(getMenuKeyboard(message.getFrom().getLanguageCode()));
            setReplyToMessageId(message.getMessageId());
            setChatId(String.valueOf(message.getChatId()));
            setText("Please select what are you do");
        }});
    }

    private ReplyKeyboardMarkup getMenuKeyboard(String locale) {
        return new ReplyKeyboardMarkup() {{
            setSelective(true);
            setResizeKeyboard(true);
            setOneTimeKeyboard(false);

            List<KeyboardRow> keyboard = new ArrayList<>();
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            keyboardFirstRow.add(resourceBundleHelper.getLocalizedString("horizontalBar", locale));
            keyboardFirstRow.add(resourceBundleHelper.getLocalizedString("bars", locale));
            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboardSecondRow.add(resourceBundleHelper.getLocalizedString("run", locale));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            setKeyboard(keyboard);
        }};
    }

    private SendMessage defaultSuccessResponseMessage(Message message) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(message.getChatId()));
        responseMessage.setText("Okay!");
        return responseMessage;
    }

}
