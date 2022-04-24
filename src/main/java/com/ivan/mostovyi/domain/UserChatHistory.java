package com.ivan.mostovyi.domain;

import com.ivan.mostovyi.constant.UserState;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserChatHistory {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long content;

    private LocalDateTime date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserState userState;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private User user;

}
