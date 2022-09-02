package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryDeatilResp;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryPageResp;
import com.plogging.domain.Quest.service.questDiary.QuestDiaryService;
import com.plogging.global.dto.ApplicationResponse;
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
    //
    @ApiOperation(value = "Quest Diary 작성하기", notes = "Quest Diary 작성.")
    @PutMapping("/{quest_id}")
    public ApplicationResponse<QuestDiaryDeatilResp> create(
                                            @PathVariable Long quest_id,
                                            @ModelAttribute QuestDiaryReq questDiaryReq){
        return questDiaryService.create(quest_id, questDiaryReq);
    }

    @ApiOperation(value = "모든 유저들의 Quest Diary 작성하기", notes = "Quest Diary 작성.")
    @GetMapping("/all")
    public ApplicationResponse<Page<QuestDiaryPageResp>> findAll(Pageable pageable){
        return questDiaryService.findAll(pageable);
    }

    @ApiOperation(value = "Quest Diary 하나 조회", notes = "Quest Diary 하나 조회")
    @GetMapping("/{quest_diary_id}")
    public ApplicationResponse<QuestDiaryDeatilResp> findById(@PathVariable Long quest_diary_id){
        return questDiaryService.findById(quest_diary_id);
    }

    @ApiOperation(value = "특정 Quest의 Quest Diary 전체 조회하기", notes = "특정 Quest의 Quest Diary들 조회.")
    @GetMapping("/{quest_id}")
    public ApplicationResponse<Page<QuestDiaryPageResp>> findAllByUserAndQuest(@PathVariable Long quest_id, Pageable pageable){
        return questDiaryService.findAllByUserAndQuest(quest_id, pageable);
    }
}