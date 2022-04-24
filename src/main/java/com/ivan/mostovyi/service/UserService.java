package com.ivan.mostovyi.service;

import com.ivan.mostovyi.constant.UserState;
import com.ivan.mostovyi.domain.User;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserService {

    User getUser(Message message);

    UserState getUserState(Long userId, Long chatId);

    void setUserState(Message message, UserState state);

}
