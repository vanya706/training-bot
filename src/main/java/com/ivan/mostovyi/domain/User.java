package com.ivan.mostovyi.domain;

import com.ivan.mostovyi.constant.UserState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "telegram_user")
public class User {

    @Id
    private Long id;

    @Column(nullable = false)
    private Long chatId;

    @Enumerated(EnumType.STRING)
    private UserState state;

    @OneToMany(mappedBy = "user")
    private List<UserChatHistory> userChatHistories;

}
