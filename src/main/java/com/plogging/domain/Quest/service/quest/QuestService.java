package com.plogging.domain.Quest.service.quest;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.User.entity.User;
import com.plogging.global.dto.ApplicationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestService {

    /* 퀘스트 등록하기 */
    ApplicationResponse<QuestRes> create(CreateQuestReq createQuestReq);

    /* 퀘스트 하나 조회 */
    ApplicationResponse<QuestRes> findById(Long id);
    ApplicationResponse<Page<QuestRes>> findAll(Pageable pageable);

    /* 퀘스트 페이징 조회 */
    ApplicationResponse<QuestRes> edit(Long id, EditQuestReq editQuestReq);

    /* 퀘스트 삭제 */
    ApplicationResponse<Void> deleteById(Long id);

    /* 회원가입 시에 모든 퀘스트들을 가입한 User의 ProceedingQuest로 등록하기 */
    ApplicationResponse<Void> makeAllQuestProceeding(User user);

    /* 특정 퀘스트를 완료하기 */
    ApplicationResponse<Void> completeQuest(UserQuestProceeding userQuestProceeding, Quest quest, User user);





    /* --- server 내에서만 사용하는 메서드들 (나중에 클래스 분리 예정) --- */

    List<Quest> findAllOG();

}
