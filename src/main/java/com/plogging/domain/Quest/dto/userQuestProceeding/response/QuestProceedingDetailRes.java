package com.plogging.domain.Quest.dto.userQuestProceeding.response;

import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.global.utill.DateChanger;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.Data;

@Data
public class QuestProceedingDetailRes {
    private Long questProceedingId;
    private Long questId;
    private String name;
    private String photo;
    private int level;
    private int gage;
    private String startTime;

    public static QuestProceedingDetailRes create(UserQuestProceeding userQuestProceeding){
        QuestProceedingDetailRes questProceedingDetailRes = new QuestProceedingDetailRes();
        questProceedingDetailRes.questProceedingId = userQuestProceeding.getId();
        questProceedingDetailRes.questId = userQuestProceeding.getQuest().getId();
        questProceedingDetailRes.name = userQuestProceeding.getQuest().getName();
        questProceedingDetailRes.photo = AwsS3Service.makeUrlOfFilename(userQuestProceeding.getQuest().getPhoto());
        questProceedingDetailRes.level = userQuestProceeding.getLevel();
        questProceedingDetailRes.gage = userQuestProceeding.getGage();
        questProceedingDetailRes.startTime = DateChanger.changefrom(userQuestProceeding.getStartTime().toString());
        return questProceedingDetailRes;
    }
}
