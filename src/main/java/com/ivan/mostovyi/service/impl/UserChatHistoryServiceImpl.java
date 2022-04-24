package com.ivan.mostovyi.service.impl;

import com.ivan.mostovyi.constant.UserState;
import com.ivan.mostovyi.domain.User;
import com.ivan.mostovyi.domain.UserChatHistory;
import com.ivan.mostovyi.repository.UserChatHistoryRepository;
import com.ivan.mostovyi.service.UserChatHistoryService;
import com.ivan.mostovyi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class UserChatHistoryServiceImpl implements UserChatHistoryService {

    private final UserChatHistoryRepository repository;

    private final UserService userService;

    @Override
    public void create(Message message, UserState userState) {

        User user = userService.getUser(message);

        UserChatHistory userChatHistory = UserChatHistory.builder()
                .content(Long.valueOf(message.getText()))
                .userState(userState)
                .user(user)
                .date(LocalDateTime.ofInstant(Instant.ofEpochSecond(message.getDate()), ZoneId.systemDefault()))
                .build();

        repository.save(userChatHistory);
    }

}
