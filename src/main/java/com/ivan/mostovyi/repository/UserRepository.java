package com.ivan.mostovyi.repository;

import com.ivan.mostovyi.constant.UserState;
import com.ivan.mostovyi.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT state FROM User WHERE id = :id AND chatId = :chatId")
    Optional<UserState> findStateByIdAndChatId(@Param("id") Long id, @Param("chatId") Long chatId);

}
