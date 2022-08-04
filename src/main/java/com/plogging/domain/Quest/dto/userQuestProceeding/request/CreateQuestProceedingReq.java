package com.plogging.domain.Quest.dto.userQuestProceeding.request;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.User.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateQuestProceedingReq {

    private User user;

    public UserQuestProceeding toEntityWith(Quest quest) {
        return UserQuestProceeding.builder()
                .level(1)
                .gage(0)
                .startTime(LocalDateTime.now())
                .user(this.user)
                .quest(quest)
                .build();
    }
}
