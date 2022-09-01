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
public class QuestDiaryDeatilResp {
    private Long questDiaryId;
    private Long questId;
    private String comment;
    private String photo;
    private String writer;

//    public QuestDiaryDeatilResp(Quest quest, User user, String comment, String filename){
//        this.questId = quest.getId();
//        this.comment = comment;
//        this.photo = AwsS3Service.makeUrlOfFilename(filename);
//    }

    public static QuestDiaryDeatilResp create(UserQuestDiary userQuestDiary) {
        QuestDiaryDeatilResp questDiaryDeatilResp = new QuestDiaryDeatilResp();
        questDiaryDeatilResp.questDiaryId = userQuestDiary.getId();
        questDiaryDeatilResp.questId = userQuestDiary.getQuest().getId();
        questDiaryDeatilResp.comment = userQuestDiary.getComment();
        questDiaryDeatilResp.photo = AwsS3Service.makeUrlOfFilename(userQuestDiary.getPhoto());
        questDiaryDeatilResp.writer = userQuestDiary.getUser().getNickName();
        return questDiaryDeatilResp;
    }
}
