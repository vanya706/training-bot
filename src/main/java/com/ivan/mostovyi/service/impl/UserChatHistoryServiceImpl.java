package com.ivan.mostovyi.service.impl;

import com.ivan.mostovyi.repository.UserChatHistoryRepository;
import com.ivan.mostovyi.service.UserChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserChatHistoryServiceImpl implements UserChatHistoryService {

    private final UserChatHistoryRepository repository;

}
