package com.plogging.domain.Quest.service.quest;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestService {

    // C
    ApplicationResponse<QuestRes> create(CreateQuestReq createQuestReq);

    // R
    ApplicationResponse<QuestRes> findById(Long id);
    ApplicationResponse<Page<QuestRes>> findAll(Pageable pageable);

    // U
    ApplicationResponse<QuestRes> edit(Long id, EditQuestReq editQuestReq);

    // D
    ApplicationResponse<Void> deleteById(Long id);

    /* --- server 내에서만 사용하는 메서드들 (나중에 클래스 분리 예정) --- */

    List<Quest> findAllOG();

}
