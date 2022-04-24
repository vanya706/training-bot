package com.ivan.mostovyi.service;

import com.ivan.mostovyi.constant.UserState;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface UserChatHistoryService {

    void create(Message message, UserState userState);

}
