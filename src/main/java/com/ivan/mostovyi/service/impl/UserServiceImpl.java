package com.ivan.mostovyi.service.impl;

import com.ivan.mostovyi.repository.UserRepository;
import com.ivan.mostovyi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

}
