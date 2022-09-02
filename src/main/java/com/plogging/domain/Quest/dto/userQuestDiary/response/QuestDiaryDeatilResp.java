package com.plogging.domain.Quest.dto.userQuestDiary.response;

import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.User.entity.User;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class QuestDiaryDeatilResp{
    private Long questDiaryId;
    private Long questId;
    private String comment;
    private List<String> photo;
    private String writer;
    private String createdAt;

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
        AwsS3Service.makeUrlsOfFilenames(userQuestDiary.getFilenames());
        questDiaryDeatilResp.writer = userQuestDiary.getUser().getNickName();
        return questDiaryDeatilResp;
    }
}
