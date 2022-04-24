package com.ivan.mostovyi.bot.handler;

import com.ivan.mostovyi.constant.UserState;
import com.ivan.mostovyi.helper.ResourceBundleHelper;
import com.ivan.mostovyi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class IncomingMessageHandler {

    private final ResourceBundleHelper resourceBundleHelper;

    private final UserService userService;


    public SendMessage handleIncomingMessage(Message message) {
        UserState userState = userService.getUserState(message.getFrom().getId(), message.getChatId());

        switch (userState) {
            case START:
            case HELP:
                if (message.getText().equals(getRunCommand(message))) {
                    return onRunChosen(message);
                }
                if (message.getText().equals(getBarsCommand(message))) {
                    return onBarsChosen(message);
                }
                if (message.getText().equals(getHorizontalBarCommand(message))) {
                    return onHorizontalBarChosen(message);
                }
                return onMenuChosen(message);
            case HORIZONTAL_BAR:
                break;
            case BARS:
                break;
            case RUN:
                break;
        }
        return null;
    }

    private SendMessage onHorizontalBarChosen(Message message) {
        userService.setUserState(message, UserState.HORIZONTAL_BAR);
        return null;
    }

    private SendMessage onBarsChosen(Message message) {
        userService.setUserState(message, UserState.BARS);
        return null;
    }

    private SendMessage onRunChosen(Message message) {
        userService.setUserState(message, UserState.RUN);
        return null;
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

    private SendMessage onMenuChosen(Message message) {
        return new SendMessage() {{
            enableMarkdown(true);
            setReplyMarkup(getMenuKeyboard(message.getFrom().getLanguageCode()));
            setReplyToMessageId(message.getMessageId());
            setChatId(String.valueOf(message.getChatId()));
            setText("Please select what are you do");
        }};
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

}
