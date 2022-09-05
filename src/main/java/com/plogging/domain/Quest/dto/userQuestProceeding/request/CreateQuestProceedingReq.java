package com.plogging.domain.Quest.dto.userQuestProceeding.request;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.plogging.domain.Quest.VALUE.INIT_GAGE;
import static com.plogging.domain.Quest.VALUE.INIT_LEVEL;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateQuestProceedingReq {

    private User user;

    public static CreateQuestProceedingReq create(User user){
        CreateQuestProceedingReq createQuestProceedingReq = new CreateQuestProceedingReq();
        createQuestProceedingReq.user = user;
        List<UserQuestProceeding> userQuestProceedings = user.getUserQuestProceedings();
        return createQuestProceedingReq;
    }

    public UserQuestProceeding toEntityWith(Quest quest) {
        return UserQuestProceeding.create(INIT_LEVEL, INIT_GAGE, this.user, quest);
    }
}
