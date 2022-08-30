package com.plogging.domain.Quest.service.questComplete;

import com.amazonaws.services.kms.model.NotFoundException;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.dto.userQuestComplete.response.QuestCompRes;
import com.plogging.domain.Quest.exception.QuestCompleteIdNotFoundException;
import com.plogging.domain.Quest.repository.QuestCompleteRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class QuestCompleteServiceImpl implements QuestCompleteService{

    private final QuestCompleteRepository questCompleteRepository;
    private final UserRepository userRepository;

    @Override
    public ApplicationResponse<QuestCompRes> findById(Long id){
        return ApplicationResponse.ok(
                QuestCompRes.create(
                        questCompleteRepository.findById(id).orElseThrow(()-> new QuestCompleteIdNotFoundException(id)))
        );
    }

    @Override
    public ApplicationResponse<Page<QuestCompRes>> findAll(Pageable pageable, Long userIdx){
        User user = userRepository.findById(userIdx).orElseThrow(NotFoundUserException::new);
        return ApplicationResponse.ok(
                questCompleteRepository.findAllByUser(pageable, user).map(QuestCompRes::create)
        );
    }
}
