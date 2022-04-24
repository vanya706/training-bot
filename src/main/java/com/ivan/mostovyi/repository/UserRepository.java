package com.ivan.mostovyi.repository;

import com.ivan.mostovyi.constant.UserState;
import com.ivan.mostovyi.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<UserState> findStateByIdAndChatId(Long id, Long chatId);

}
