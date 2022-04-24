package com.ivan.mostovyi.repository;

import com.ivan.mostovyi.domain.UserChatHistory;
import org.springframework.data.repository.CrudRepository;

public interface UserChatHistoryRepository extends CrudRepository<UserChatHistory, Long> {

}
