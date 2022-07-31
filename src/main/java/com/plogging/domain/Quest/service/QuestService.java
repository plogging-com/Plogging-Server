package com.plogging.domain.Quest.service;

import com.plogging.domain.Quest.dto.quest.request.QuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.repository.QuestRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestService {

    private final QuestRepository questRepository;
    // private final S3Service s3Service;


    @Transactional
    public ApplicationResponse<QuestRes> questCreate(QuestReq questReq) {
        String imageURL = "www.s3-plogging.aws불라불라";// s3Service.makeImage(questReq.getPhoto());
        Quest quest = questRepository.save(questReq.toEntity(imageURL));
        QuestRes questRes = QuestRes.create(quest);
        return ApplicationResponse.create("created", questRes);
    }
}
