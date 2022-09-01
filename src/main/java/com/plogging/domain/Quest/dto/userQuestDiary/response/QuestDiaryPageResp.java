package com.plogging.domain.Quest.dto.userQuestDiary.response;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.User.entity.User;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QuestDiaryPageResp {
    private Long questDiaryId;
    private Long questId;
    private String writer;

    @Builder
    public QuestDiaryPageResp(Quest quest, User user){
        this.questId = quest.getId();
    }

    public static QuestDiaryPageResp create(UserQuestDiary userQuestDiary) {
        QuestDiaryPageResp questDiaryResp = new QuestDiaryPageResp();
        questDiaryResp.questDiaryId = userQuestDiary.getId();
        questDiaryResp.questId = userQuestDiary.getQuest().getId();
        questDiaryResp.writer = userQuestDiary.getUser().getNickName();
        return questDiaryResp;
    }
}
