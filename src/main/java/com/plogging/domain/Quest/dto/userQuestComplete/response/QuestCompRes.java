package com.plogging.domain.Quest.dto.userQuestComplete.response;

import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestCompRes {
    private Long questCompleteId;
    private String name;
    private String photo;
    private int level;
    private String endTime;

    public static QuestCompRes create(UserQuestComplete userQuestComplete) {
        QuestCompRes questCompRes = new QuestCompRes();
        questCompRes.questCompleteId = userQuestComplete.getId();
        questCompRes.name = userQuestComplete.getQuest().getName();
        questCompRes.photo = AwsS3Service.makeUrlOfFilename(userQuestComplete.getQuest().getPhoto());
        questCompRes.level = userQuestComplete.getLevel();
        questCompRes.endTime = userQuestComplete.getEndTime().toString();
        return questCompRes;
    }
}
