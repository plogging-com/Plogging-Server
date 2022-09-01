package com.plogging.domain.Quest.service.questComplete;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.dto.userQuestComplete.response.QuestCompRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestCompleteService {
    // R
    ApplicationResponse<QuestCompRes> findById(Long id);
    ApplicationResponse<Page<QuestCompRes>> findAll(Pageable pageable);
}
