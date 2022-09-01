package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryResp;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.Quest.service.questDiary.QuestDiaryService;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.UserIDValidException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.jwt.service.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/quests-diary")
@Api(tags = "Quest API")
public class QuestDiaryController{

    private final QuestDiaryService questDiaryService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @ApiOperation(value = "Quest Diary 작성하기", notes = "Quest Diary 작성.")
    @PutMapping("/{complete_quest_id}")
    public ApplicationResponse<QuestDiaryResp> create(
                                            @PathVariable Long complete_quest_id,
                                            @ModelAttribute QuestDiaryReq questDiaryReq){
        return questDiaryService.create(complete_quest_id, questDiaryReq);
    }

    @ApiOperation(value = "Quest Diary 작성하기", notes = "Quest Diary 작성.")
    @GetMapping("/{quest_diary_id}")
    public ApplicationResponse<QuestDiaryResp> findById(@PathVariable Long quest_diary_id){
        return questDiaryService.findById(quest_diary_id);
    }

    @ApiOperation(value = "Quest Diary 작성하기", notes = "Quest Diary 작성.")
    @GetMapping("/}")
    public ApplicationResponse<Page<QuestDiaryResp>> findAll(Pageable pageable){
        String userLoginId = jwtService.getLoginId();
        User user = userRepository.findByLoginId(userLoginId).orElseThrow(UserIDValidException::new);
        return questDiaryService.findAllByUser(pageable, user);
    }
}
