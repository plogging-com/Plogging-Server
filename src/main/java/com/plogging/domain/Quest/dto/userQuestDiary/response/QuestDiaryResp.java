package com.plogging.domain.Quest.dto.userQuestDiary.response;

import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.User.dto.response.UserFindRes;
import com.plogging.domain.User.entity.User;
import lombok.Data;

@Data
public class QuestDiaryResp {
    private QuestRes questRes;
    private UserFindRes userFindRes;
    private String comment;
    private String photo;


    public static QuestDiaryResp create(Quest quest, User user, String comment, String photo) {
        QuestDiaryResp questDiaryResp = new QuestDiaryResp();
        questDiaryResp.questRes = QuestRes.create(quest);
        questDiaryResp.userFindRes = UserFindRes.from(user);
        questDiaryResp.comment = comment;
        questDiaryResp.photo = photo;
        return questDiaryResp;
    }
}
