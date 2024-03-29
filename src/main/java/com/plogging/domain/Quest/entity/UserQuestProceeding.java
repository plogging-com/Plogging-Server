package com.plogging.domain.Quest.entity;


import com.plogging.domain.User.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.plogging.domain.Quest.VALUE.MAX_GAGE;

@Entity
@Getter
@NoArgsConstructor
public class UserQuestProceeding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userQuestProceedingIdx")
    private Long id;

    private Integer level;
    private int gage;
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questIdx")
    private Quest quest;

//    @Builder
//    public UserQuestProceeding(int level, int gage, LocalDateTime startTime, User user, Quest quest) {
//        this.level = level;
//        this.gage = gage;
//        this.startTime = startTime;
//        this.addUser(user);
//        this.addQuest(quest);
//    }

    public static UserQuestProceeding create(int level, int gage, User user, Quest quest) {
        UserQuestProceeding userQuestProceeding = new UserQuestProceeding();
        userQuestProceeding.level = level;
        userQuestProceeding.gage = gage;
        userQuestProceeding.startTime = LocalDateTime.now();
        userQuestProceeding.addUser(user);
        userQuestProceeding.addQuest(quest);
        return userQuestProceeding;
    }

    @Builder
    public UserQuestProceeding(User user, Quest quest) {
        this.level = 1;
        this.gage = 0;
        this.startTime = LocalDateTime.now();
        this.addUser(user);
        this.addQuest(quest);
    }

    private void addQuest(Quest quest) {
        this.quest = quest;
        quest.addProceedingQuest(this);
    }

    private void addUser(User user) {
        this.user = user;
        user.addProceedingQuest(this);
    }

    public boolean completeAll() {
        if(this.gage != 100) return false;
        return this.level == 4;
    }

    public void levelUp() {
        if(gage == 100){
            this.level++;
            this.gage = 0;
        }
    }

    public boolean isOverMaxLevel(Integer maxLevel) {
        return this.level > maxLevel;
    }

    public boolean isMaxGage() {
        return this.gage >= MAX_GAGE;
    }

    public void gageUp(int value) {
        this.gage += value;
    }
}
