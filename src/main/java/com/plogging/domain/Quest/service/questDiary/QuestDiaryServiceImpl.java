package com.plogging.domain.Quest.service.questDiary;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryResp;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.Quest.exception.QuestCompleteIdNotFoundException;
import com.plogging.domain.Quest.repository.QuestCompleteRepository;
import com.plogging.domain.Quest.repository.QuestDiaryRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class QuestDiaryServiceImpl implements QuestDiaryService {

    private final QuestDiaryRepository questDiaryRepository;
    private final QuestCompleteRepository questCompleteRepository;
    private final AwsS3Service awsS3Service;


    @Transactional
    @Override
    public ApplicationResponse<QuestDiaryResp> create(Long completeQuestId, QuestDiaryReq questDiaryReq) {
        UserQuestComplete userQuestComplete = questCompleteRepository
                .findById(completeQuestId).orElseThrow(() -> new QuestCompleteIdNotFoundException(completeQuestId));

        Quest quest = userQuestComplete.getQuest();
        User user = userQuestComplete.getUser();

        String filename = awsS3Service.uploadImage(questDiaryReq.getPhoto());
        UserQuestDiary userQuestDiary = UserQuestDiary.create(questDiaryReq, filename, quest, user);
        questDiaryRepository.save(userQuestDiary);

        QuestDiaryResp questDiaryResp = QuestDiaryResp.builder()
                .quest(quest)
                .user(user)
                .comment(questDiaryReq.getComment())
                .photo(AwsS3Service.makeUrlOfFilename(filename)).build();

        return ApplicationResponse.create("created", questDiaryResp);
    }
}
