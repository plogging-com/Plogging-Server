package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.dto.userQuestProceeding.response.QuestProceedingRes;
import com.plogging.domain.Quest.service.quest.QuestService;
import com.plogging.domain.Quest.service.questProceeding.QuestProceedingService;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/quests-proceeding")
@Api(tags = "Quest API")
public class QuestProceedingController {

    private final QuestProceedingService questProceedingService;

    @ApiOperation(value = "진행중인 quest 전체 조회", notes = "진행중 quest 전체 조회.")
    @GetMapping("/users/{user_idx}")
    public ApplicationResponse<Page<QuestProceedingRes>> findAll(@PathVariable Long user_idx, Pageable pageable) {
        return questProceedingService.findAll(pageable, user_idx);
    }

    @ApiOperation(value = "진행중인 quest 한 개 id로 조회", notes = "진행중 quest 조회.")
    @GetMapping("/{id}")
    public ApplicationResponse<QuestProceedingRes> findById(@PathVariable Long id) {
        return questProceedingService.findById(id);
    }

    @ApiOperation(value = "진행중인 quest 한 개 gage 상승시키기", notes = "quest 한 개 gage 상승.")
    @PutMapping("/gageup/{id}")
    public ApplicationResponse<Void> gageUp(@PathVariable Long id, int value) {
        return questProceedingService.gageUp(id, value);
    }
}
