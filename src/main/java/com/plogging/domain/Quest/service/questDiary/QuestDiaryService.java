package com.plogging.domain.Quest.service.questDiary;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryResp;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.User.entity.User;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestDiaryService {
    ApplicationResponse<QuestDiaryResp> create(Long complete_quest_id, QuestDiaryReq questDiaryReq);

    ApplicationResponse<QuestDiaryResp> findById(Long quest_diary_id);

    ApplicationResponse<Page<QuestDiaryResp>> findAllByUser(Pageable pageable, User user);
}
