package com.plogging.domain.Quest.dto.userQuestProceeding.response;

import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.Data;

@Data
public class QuestProceedingPageRes {
    private Long questProceedingId;
    private String name;
    private String photo;

    public static QuestProceedingPageRes create(UserQuestProceeding userQuestProceeding){
        QuestProceedingPageRes questProceedingRes = new QuestProceedingPageRes();
        questProceedingRes.questProceedingId = userQuestProceeding.getId();
        questProceedingRes.name = userQuestProceeding.getQuest().getName();
        questProceedingRes.photo = AwsS3Service.makeUrlOfFilename(userQuestProceeding.getQuest().getPhoto());
        return questProceedingRes;
    }
}
