package com.plogging.domain.Quest.service.questDiary;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryResp;
import com.plogging.global.dto.ApplicationResponse;

public interface QuestDiaryService {
    ApplicationResponse<QuestDiaryResp> create(Long complete_quest_id, QuestDiaryReq questDiaryReq);
}
