package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.dto.userQuestComplete.response.QuestCompRes;
import com.plogging.domain.Quest.service.quest.QuestService;
import com.plogging.domain.Quest.service.questComplete.QuestCompleteService;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/quests-complete")
@Api(tags = "Quest API")
public class QuestCompleteController{

    private final QuestCompleteService questCompleteService;

    @ApiOperation(value = "모든 완료된 quest 목록 조회", notes = "quest 전체 조회.")
    @GetMapping("/users/{user_idx}")
    public ApplicationResponse<Page<QuestCompRes>> findAll(Pageable pageable, @PathVariable Long user_idx){
        return questCompleteService.findAll(pageable, user_idx);
    }
}