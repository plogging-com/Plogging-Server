package com.plogging.domain.Quest.service.questProceeding;

import com.plogging.domain.Quest.dto.userQuestProceeding.request.CreateQuestProceedingReq;
import com.plogging.domain.Quest.dto.userQuestProceeding.response.QuestProceedingRes;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestProceedingService {

    // C
    ApplicationResponse<List<QuestProceedingRes>> initAllQuest(CreateQuestProceedingReq createQuestReq);

    // R
    ApplicationResponse<List<QuestProceedingRes>> findById(Long id);
    ApplicationResponse<List<QuestProceedingRes>> findAll(Pageable pageable);

    // U
    ApplicationResponse<List<QuestProceedingRes>> levelUp(Long id);//현재 진행중인 퀘스트의 level을 올림. 단 1씩만 증가시킨다.
    ApplicationResponse<List<QuestProceedingRes>> gageUp(Long id, int value);// value 만큼의 gage를 상승시킴

    // D
    ApplicationResponse<List<QuestProceedingRes>> deleteById(Long id);
}
