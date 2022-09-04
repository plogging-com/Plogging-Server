package com.plogging.domain.Quest.service.questDiary;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryDeatilResp;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryPageResp;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.Quest.exception.NothingToShowException;
import com.plogging.domain.Quest.exception.QuestCompleteIdNotFoundException;
import com.plogging.domain.Quest.exception.QuestIdNotFoundException;
import com.plogging.domain.Quest.repository.QuestCompleteRepository;
import com.plogging.domain.Quest.repository.QuestDiaryRepository;
import com.plogging.domain.Quest.repository.QuestRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.UserException;
import com.plogging.domain.User.exception.UserIDValidException;
import com.plogging.domain.User.exception.UserIdDuplicationException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.jwt.service.JwtService;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class QuestDiaryServiceImpl implements QuestDiaryService {

    private final QuestDiaryRepository questDiaryRepository;
    private final QuestRepository questRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AwsS3Service awsS3Service;


    @Transactional
    @Override
    public ApplicationResponse<QuestDiaryDeatilResp> create(Long questId, QuestDiaryReq questDiaryReq) {
        Quest quest = questRepository
                .findById(questId).orElseThrow(() -> new QuestIdNotFoundException(questId));
        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(UserIdDuplicationException::new);
        List<String> filenames = awsS3Service.uploadImages(questDiaryReq.getPhotos());
        UserQuestDiary userQuestDiary = UserQuestDiary.create(questDiaryReq, filenames, quest, user);
        questDiaryRepository.save(userQuestDiary);
        QuestDiaryDeatilResp questDiaryDeatilResp = QuestDiaryDeatilResp.create(userQuestDiary);
        return ApplicationResponse.create("created", questDiaryDeatilResp);
    }

    @Override
    public ApplicationResponse<QuestDiaryDeatilResp> findById(Long questDiaryId) {
        return ApplicationResponse.ok(QuestDiaryDeatilResp.create(questDiaryRepository.findById(questDiaryId).orElseThrow(() -> new QuestIdNotFoundException(questDiaryId))));
    }

    @Override
    public ApplicationResponse<Page<QuestDiaryPageResp>> findAllByUser(Pageable pageable){
        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(UserIDValidException::new);
        Page<UserQuestDiary> result = questDiaryRepository.findAllByUser(pageable, user);
        checkContentIsEmpty(result.getNumberOfElements());
        return ApplicationResponse.ok(questDiaryRepository.findAllByUser(pageable, user).map(QuestDiaryPageResp::create));
    }

    @Override
    public ApplicationResponse<Page<QuestDiaryPageResp>> findAllByUserAndQuest(Long questId, Pageable pageable){
        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(UserIDValidException::new);
        Quest quest = questRepository.findById(questId).orElseThrow(() -> new QuestIdNotFoundException(questId));
        Page<UserQuestDiary> result = questDiaryRepository.findAllByUserAndQuest(pageable, user, quest);
        checkContentIsEmpty(result.getNumberOfElements());
        return ApplicationResponse.ok(questDiaryRepository.findAllByUserAndQuest(pageable, user, quest).map(QuestDiaryPageResp::create));
    }

    @Override
    public ApplicationResponse<Page<QuestDiaryPageResp>> findAll(Pageable pageable) {
        Page<UserQuestDiary> result = questDiaryRepository.findAll(pageable);
        checkContentIsEmpty(result.getNumberOfElements());
        return ApplicationResponse.ok(result.map(QuestDiaryPageResp::create));
    }

    private void checkContentIsEmpty(int numberOfElements) {
        if(numberOfElements == 0) throw new NothingToShowException();
    }
}
