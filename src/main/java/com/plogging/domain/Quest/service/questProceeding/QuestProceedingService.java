package com.plogging.domain.Quest.service.questProceeding;

import com.plogging.domain.Quest.dto.userQuestProceeding.request.CreateQuestProceedingReq;
import com.plogging.domain.Quest.dto.userQuestProceeding.response.QuestProceedingRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestProceedingService {

    // C
    ApplicationResponse<List<QuestProceedingRes>> initAllQuest(CreateQuestProceedingReq createQuestReq);

    // R
    ApplicationResponse<QuestProceedingRes> findById(Long id);
    ApplicationResponse<Page<QuestProceedingRes>> findAll(Pageable pageable);

    // U
    ApplicationResponse<Void> gageUp(Long id, int value);// value 만큼의 gage를 상승시킴

    // D
    ApplicationResponse<Void> deleteById(Long id);
}
