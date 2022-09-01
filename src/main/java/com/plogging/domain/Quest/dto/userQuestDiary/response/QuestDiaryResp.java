package com.plogging.domain.Quest.dto.userQuestDiary.response;

import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.User.dto.response.UserFindRes;
import com.plogging.domain.User.entity.User;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class QuestDiaryResp {
    private Long questDiaryId;
    private Long questId;
    private String comment;
    private String photo;

    public static QuestDiaryResp create(Quest quest, User user, String comment, String photo) {
        QuestDiaryResp questDiaryResp = new QuestDiaryResp();
        questDiaryResp.questId = quest.getId();
        questDiaryResp.comment = comment;
        questDiaryResp.photo = AwsS3Service.makeUrlOfFilename(photo);
        return questDiaryResp;
    }

    @Builder
    public QuestDiaryResp(Quest quest, User user, String comment, String filename){
        this.questId = quest.getId();
        this.comment = comment;
        this.photo = AwsS3Service.makeUrlOfFilename(filename);
    }

    public static QuestDiaryResp create(UserQuestDiary userQuestDiary) {
        QuestDiaryResp questDiaryResp = new QuestDiaryResp();
        questDiaryResp.questDiaryId = userQuestDiary.getId();
        questDiaryResp.questId = userQuestDiary.getQuest().getId();
        questDiaryResp.comment = userQuestDiary.getComment();
        questDiaryResp.photo = AwsS3Service.makeUrlOfFilename(userQuestDiary.getPhoto());
        return questDiaryResp;
    }
}
