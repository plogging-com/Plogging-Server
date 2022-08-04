package com.plogging.domain.Quest.service.quest;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.exception.QuestIdNotFoundException;
import com.plogging.domain.Quest.repository.QuestRepository;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestServiceImpl implements QuestService{

    private final QuestRepository questRepository;
    // private final S3Service s3Service; //TODO

    @Transactional
    @Override
    public ApplicationResponse<QuestRes> create(CreateQuestReq createQuestReq) {
        String photoURL = "";
        if(createQuestReq.getPhoto()!=null) photoURL = "www.s3-plogging.aws불라불라";// s3Service.makeImage(createQuestReq.getPhoto());
        Quest quest = questRepository.save(createQuestReq.toEntityWithPhoto(photoURL));
        return ApplicationResponse.create("created", QuestRes.create(quest));
    }

    @Override
    public ApplicationResponse<QuestRes> findById(Long id) {
        return ApplicationResponse.ok(QuestRes.create(
                questRepository.findById(id).orElseThrow(() -> new QuestIdNotFoundException(id))
        ));
    }

    @Override
    public ApplicationResponse<Page<QuestRes>> findAll(Pageable pageable) {

        return ApplicationResponse.ok(questRepository.findAll(pageable).map(QuestRes::create));
    }

    @Transactional
    @Override
    public ApplicationResponse<QuestRes> edit(Long id, EditQuestReq editQuestReq) {
        Quest quest = questRepository.findById(id).orElseThrow(() -> new QuestIdNotFoundException(id));
        String photoURL = "";
        if(editQuestReq.getPhoto()!=null) photoURL = "www.s3-plogging.aws불라불라";
        quest.edit(editQuestReq.getName(), photoURL);
        return ApplicationResponse.ok(QuestRes.create(quest));
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> deleteById(Long id) {
        questRepository.deleteById(id);
        return ApplicationResponse.ok();
    }

    @Override
    public List<Quest> findAllOG() {
        return questRepository.findAll();
    }
}
