package com.plogging.domain.Quest.dto.userQuestComplete.response;

import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestCompRes {
    private String name;
    private String imageUrl;
    private int level;
    private String endTime;

    public static QuestCompRes create(UserQuestComplete userQuestComplete) {
        QuestCompRes questCompRes = new QuestCompRes();
        questCompRes.name = userQuestComplete.getQuest().getName();
        questCompRes.imageUrl = userQuestComplete.getQuest().getPhoto(); //TODO
        questCompRes.level = userQuestComplete.getLevel();
        questCompRes.endTime = userQuestComplete.getEndTime().toString();
        return questCompRes;
    }
}
