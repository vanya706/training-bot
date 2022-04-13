package com.ivan.mostovyi.repository;

import com.ivan.mostovyi.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<String, User> {

}
