package com.ivan.mostovyi.service.impl;

import com.ivan.mostovyi.constant.UserState;
import com.ivan.mostovyi.domain.User;
import com.ivan.mostovyi.repository.UserRepository;
import com.ivan.mostovyi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;


    @Override
    public UserState getUserState(Long userId, Long chatId) {
        return repository.findStateByIdAndChatId(userId, chatId)
                .orElse(UserState.START);
    }

    @Override
    public void setUserState(Message message, UserState state) {
        User user = repository.findById(message.getFrom().getId())
                .orElseGet(() -> getUserFromMessage(message));

        user.setState(state);

        repository.save(user);
    }

    private User getUserFromMessage(Message message) {
        return new User() {{
            setId(message.getFrom().getId());
            setChatId(message.getChatId());
        }};
    }

}
