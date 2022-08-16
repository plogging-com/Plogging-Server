package com.plogging.domain.Quest.dto.userQuestProceeding.response;

import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestProceedingRes {
    private QuestRes questRes;
    private int level;
    private int gage;
    private String startTime;

    public static List<QuestProceedingRes> createInitialListRes(List<Quest> quests) {
        ArrayList<QuestProceedingRes> result = new ArrayList<>();
//        quests.forEach((q) -> result.add(new QuestProceedingRes(q)));
        return result;
    }

    public static QuestProceedingRes create(UserQuestProceeding userQuestProceeding) {
        QuestProceedingRes questProceedingRes = new QuestProceedingRes();
        questProceedingRes.questRes = new QuestRes(
                userQuestProceeding.getQuest().getName(),
                userQuestProceeding.getQuest().getPhoto());
        questProceedingRes.level = userQuestProceeding.getLevel();
        questProceedingRes.gage = userQuestProceeding.getGage();
        questProceedingRes.startTime = userQuestProceeding.getStartTime().toString();
        return questProceedingRes;
    }
}
