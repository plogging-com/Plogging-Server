package com.plogging.domain.Quest.dto.userQuestDiary.response;

import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.User.dto.response.UserFindRes;
import com.plogging.domain.User.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

    @Builder
    public QuestDiaryResp(Quest quest, User user, String comment, String photo) {
        this.questRes = QuestRes.create(quest);
        this.userFindRes = UserFindRes.from(user);
        this.comment = comment;
        this.photo = photo;
    }
}
