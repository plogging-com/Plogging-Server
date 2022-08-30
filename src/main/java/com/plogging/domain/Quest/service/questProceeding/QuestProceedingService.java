package com.plogging.domain.Quest.service.questProceeding;

import com.plogging.domain.Quest.dto.userQuestProceeding.request.CreateQuestProceedingReq;
import com.plogging.domain.Quest.dto.userQuestProceeding.response.QuestProceedingDetailRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestProceedingService {

    // C
    void initAllQuest(CreateQuestProceedingReq createQuestReq);

    // R
    ApplicationResponse<QuestProceedingDetailRes> findById(Long id);
    ApplicationResponse<Page<QuestProceedingDetailRes>> findAll(Pageable pageable, Long userIdx);

    // U
    ApplicationResponse<Void> gageUp(Long id, int value);// value 만큼의 gage를 상승시킴

    // D
    ApplicationResponse<Void> deleteById(Long id);
}
