package com.plogging.domain.Quest.service.questDiary;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryDeatilResp;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryPageResp;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestDiaryService {
    ApplicationResponse<QuestDiaryDeatilResp> create(Long quest_id, QuestDiaryReq questDiaryReq);

    ApplicationResponse<QuestDiaryDeatilResp> findById(Long quest_diary_id);

    ApplicationResponse<Page<QuestDiaryPageResp>> findAllByUser(Pageable pageable);

    ApplicationResponse<Page<QuestDiaryPageResp>> findAll(Pageable pageable);
}
