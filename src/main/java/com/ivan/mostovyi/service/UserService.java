package com.ivan.mostovyi.service;

import com.ivan.mostovyi.constant.UserState;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserService {

    UserState getUserState(Long userId, Long chatId);

    void setUserState(Message message, UserState state);

}
