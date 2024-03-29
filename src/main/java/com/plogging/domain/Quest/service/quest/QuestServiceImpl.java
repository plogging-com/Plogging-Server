package com.plogging.domain.Quest.service.quest;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.entity.Quest;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.Quest.exception.CanNotCompleteQuestException;
import com.plogging.domain.Quest.exception.NothingToShowException;
import com.plogging.domain.Quest.exception.QuestIdNotFoundException;
import com.plogging.domain.Quest.repository.QuestCompleteRepository;
import com.plogging.domain.Quest.repository.QuestProceedingRepository;
import com.plogging.domain.Quest.repository.QuestRepository;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.service.user.UserService;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestServiceImpl implements QuestService{

    private final QuestRepository questRepository;
    private final QuestProceedingRepository questProceedingRepository;
    private final QuestCompleteRepository questCompleteRepository;
    private final AwsS3Service awsS3Service;
    private Quest todayQuest;

    @Transactional
    @Override
    public ApplicationResponse<QuestRes> create(CreateQuestReq createQuestReq){
        String filename = awsS3Service.uploadImage(createQuestReq.getPhoto());
        Quest quest = questRepository.save(createQuestReq.toEntityWithPhoto(filename));
        return ApplicationResponse.create("created", QuestRes.create(quest));
    }

    @Override
    public ApplicationResponse<QuestRes> findById(Long id){
        return ApplicationResponse.ok(
                QuestRes.create(questRepository.findById(id).orElseThrow(() -> new QuestIdNotFoundException(id)))
        );
    }

    @Override
    public ApplicationResponse<Page<QuestRes>> findAll(Pageable pageable){
        return ApplicationResponse.ok(questRepository.findAll(pageable).map(QuestRes::create));
    }

    @Transactional
    @Override
    public ApplicationResponse<QuestRes> edit(Long id, EditQuestReq editQuestReq){
        Quest quest = questRepository.findById(id).orElseThrow(() -> new QuestIdNotFoundException(id));
        String filename = awsS3Service.uploadImage(editQuestReq.getPhoto());
        quest.edit(editQuestReq.getName(), editQuestReq.getMaxLevel(), filename);
        return ApplicationResponse.ok(QuestRes.create(quest));
    }

    @Transactional
    @Override
    public ApplicationResponse<Void> deleteById(Long id){
        questRepository.deleteById(id);
        return ApplicationResponse.ok();
    }

    @Transactional
    @Override/*to server*/
    public ApplicationResponse<Void> makeAllQuestProceeding(User user){
        findAllOG().forEach((q)->questProceedingRepository.save(new UserQuestProceeding(user, q)));
        return ApplicationResponse.ok();
    }

    @Transactional
    @Override /*to server*/
    public ApplicationResponse<Void> completeQuest(UserQuestProceeding userQuestProceeding, Quest quest, User user){
        // 아직 maxGage에 도달하지 않았다면 Quest를 Complete할 수 없다는 Exception을 날린다.
        if(!userQuestProceeding.isMaxGage()) throw new CanNotCompleteQuestException();
        // 해당 User가 해당 Quest를 완료했음을 저장
        questCompleteRepository.save(
                UserQuestComplete.builder().user(user).quest(quest).level(userQuestProceeding.getLevel()).build());
        // 해당 quest의 level을 증가시킴
        userQuestProceeding.levelUp();
        // quest가 완료되면 user의 growth를 up시킨다.
        user.levelUp();
        // 해당 Quest가 maxLevel을 달성했다면 진행중인 Quest에서 삭제시킨다(해당 Quest는 끝까지 달성된 것이므로)
        if(userQuestProceeding.isOverMaxLevel(quest.getMaxLevel())) questProceedingRepository.deleteById(userQuestProceeding.getId());
        // 완료 응답
        return ApplicationResponse.ok();
    }

    @Override /*to server*/
    public List<Quest> findAllOG(){
        return questRepository.findAll();
    }

    @Override
    public ApplicationResponse<QuestRes> findTodayQuest() {
        return ApplicationResponse.ok(QuestRes.create(this.todayQuest));
    }

    //    @Scheduled(cron = "0 0 6 * * ?")//매일 6시
    @Scheduled(fixedDelay=1000*60*60)//test: 일단 오늘의 quest를 1시간마다 바뀌도록.
    public void setTodayQuest() {
        List<Quest> quests = findAllOG();
        todayQuest = quests.get((int) (Math.random() * quests.size()));
        log.info("오늘의 퀘스트가 변경되었습니다. 오늘의 퀘스트:{}", todayQuest.getName());
    }

    private void checkContentIsEmpty(int numberOfElements) {
        if(numberOfElements == 0) throw new NothingToShowException();
    }
}
