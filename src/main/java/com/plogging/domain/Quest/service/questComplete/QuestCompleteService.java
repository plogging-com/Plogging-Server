package com.plogging.domain.Quest.service.questComplete;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestCompleteService {

    // C
    ApplicationResponse<QuestRes> create(CreateQuestReq createQuestReq);

    // R
    ApplicationResponse<QuestRes> findById(Long id);
    ApplicationResponse<Page<QuestRes>> findAll(Pageable pageable);

    // U
    ApplicationResponse<QuestRes> edit(Long id, EditQuestReq editQuestReq);

    // D
    ApplicationResponse<Void> deleteById(Long id);



}
