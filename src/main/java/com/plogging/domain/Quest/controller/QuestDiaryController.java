package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.Quest.dto.userQuestDiary.response.QuestDiaryResp;
import com.plogging.domain.Quest.service.questDiary.QuestDiaryService;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/quests-diary")
@Api(tags = "Quest API")
public class QuestDiaryController{

    private final QuestDiaryService questDiaryService;

    @ApiOperation(value = "Quest Diary 작성하기", notes = "Quest Diary 작성.")
    @PutMapping("/{complete_quest_id}")
    public ApplicationResponse<QuestDiaryResp> create(
                                            @PathVariable Long complete_quest_id,
                                            @ModelAttribute QuestDiaryReq questDiaryReq){
        return questDiaryService.create(complete_quest_id, questDiaryReq);
    }
}
