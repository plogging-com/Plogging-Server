package com.plogging.domain.Quest.dto.userQuestProceeding.request;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateQuestProceedingReq {

    private User user;

    public static CreateQuestProceedingReq create(User user) {
        CreateQuestProceedingReq createQuestProceedingReq = new CreateQuestProceedingReq();
        createQuestProceedingReq.user = user;
        return createQuestProceedingReq;
    }

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
