package com.plogging.domain.Quest.entity;


import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuestComplete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userQuestProceedingIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questIdx")
    private Quest quest;

    private int level;
    private LocalDateTime endTime;

    @Builder
    public UserQuestComplete(User user, Quest quest, int level) {
        this.user = user;
        this.quest = quest;
        this.level = level;
        this.endTime = LocalDateTime.now();
    }
}
