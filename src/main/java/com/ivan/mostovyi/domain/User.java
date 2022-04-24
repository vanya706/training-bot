package com.ivan.mostovyi.domain;

import com.ivan.mostovyi.constant.UserState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "telegram_user")
public class User {

    @Id
    private Long id;

    private Long chatId;

    @Enumerated(EnumType.STRING)
    private UserState state;

}
