package com.plogging.domain.Quest.service.questProceeding;

import com.plogging.domain.Quest.dto.userQuestProceeding.request.CreateQuestProceedingReq;
import com.plogging.domain.Quest.dto.userQuestProceeding.response.QuestProceedingRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.repository.QuestProceedingRepository;
import com.plogging.domain.Quest.service.quest.QuestService;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestProceedingServiceImpl implements QuestProceedingService{

    private final QuestProceedingRepository questProceedingRepository;
    private final QuestService questService;

    @Transactional
    @Override
    public ApplicationResponse<List<QuestProceedingRes>> initAllQuest(CreateQuestProceedingReq createQuestReq) {
        List<Quest> quests = questService.findAllOG();
        quests.forEach((q) -> questProceedingRepository.save(createQuestReq.toEntityWith(q)));
        return ApplicationResponse.ok(QuestProceedingRes.createInitialListRes(quests));
    }

    @Override
    public ApplicationResponse<List<QuestProceedingRes>> findById(Long id) {
        return null;
    }

    @Override
    public ApplicationResponse<List<QuestProceedingRes>> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ApplicationResponse<List<QuestProceedingRes>> levelUp(Long id) {
        return null;
    }

    @Override
    public ApplicationResponse<List<QuestProceedingRes>> gageUp(Long id, int value) {
        return null;
    }

    @Override
    public ApplicationResponse<List<QuestProceedingRes>> deleteById(Long id) {
        return null;
    }
}
