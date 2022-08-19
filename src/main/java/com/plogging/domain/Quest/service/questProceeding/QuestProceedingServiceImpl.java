package com.plogging.domain.Quest.service.questProceeding;

import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.dto.userQuestProceeding.request.CreateQuestProceedingReq;
import com.plogging.domain.Quest.dto.userQuestProceeding.response.QuestProceedingRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.Quest.exception.QuestProceedingIdNotFoundException;
import com.plogging.domain.Quest.repository.QuestProceedingRepository;
import com.plogging.domain.Quest.service.quest.QuestService;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestProceedingServiceImpl implements QuestProceedingService{

    private final QuestProceedingRepository questProceedingRepository;
    private final QuestService questService;

    @Transactional
    @Override /* to server */
    public ApplicationResponse<List<QuestProceedingRes>> initAllQuest(CreateQuestProceedingReq createQuestReq) {
        List<Quest> quests = questService.findAllOG();
        quests.forEach((q) -> questProceedingRepository.save(createQuestReq.toEntityWith(q)));
        return ApplicationResponse.ok(QuestProceedingRes.createInitialListRes(quests));
    }

    @Override
    public ApplicationResponse<QuestProceedingRes> findById(Long id){
        UserQuestProceeding userQuestProceeding =
                questProceedingRepository.findById(id).orElseThrow(() -> new QuestProceedingIdNotFoundException(id));
        return ApplicationResponse.ok(QuestProceedingRes.create(userQuestProceeding));
    }

    @Override
    public ApplicationResponse<Page<QuestProceedingRes>> findAll(Pageable pageable){
        return ApplicationResponse.ok(questProceedingRepository.findAll(pageable).map(QuestProceedingRes::create));
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> gageUp(Long id, int value){
        UserQuestProceeding userQuestProceeding =
                questProceedingRepository.findById(id).orElseThrow(() -> new QuestProceedingIdNotFoundException(id));

        userQuestProceeding.gageUp(value);

        if(userQuestProceeding.isMaxGage())
            questService.completeQuest(userQuestProceeding, userQuestProceeding.getQuest(), userQuestProceeding.getUser());

        return ApplicationResponse.ok();
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> deleteById(Long id){
        UserQuestProceeding userQuestProceeding =
                questProceedingRepository.findById(id).orElseThrow(() -> new QuestProceedingIdNotFoundException(id));
        questProceedingRepository.deleteById(id);
        return ApplicationResponse.ok();
    }
}
