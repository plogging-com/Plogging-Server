package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.service.quest.QuestServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/quests")
@Api(tags = "Quest API")
public class QuestController {

    private final QuestServiceImpl questServiceImpl;

    @ApiOperation(value = "새로운 quest 등록", notes = "quest를 등록.")
    @PostMapping("/quest")
    public ApplicationResponse<QuestRes> create(@ModelAttribute CreateQuestReq createQuestReq) {
        return questServiceImpl.create(createQuestReq);
    }

    @ApiOperation(value = "quest 한 개 id로 조회", notes = "quest id로 조회.")
    @GetMapping("/quest/{quest_id}")
    public ApplicationResponse<QuestRes> findById(@PathVariable Long quest_id) {
        return questServiceImpl.findById(quest_id);
    }

    @ApiOperation(value = "모든 quest 목록 조회", notes = "quest 전체 조회.")
    @GetMapping("/quest")
    public ApplicationResponse<Page<QuestRes>> findAll(Pageable pageable) {
        return questServiceImpl.findAll(pageable);
    }

    @ApiOperation(value = "quest 정보(photo, name) 수정", notes = "quest 정보 수정.")
    @PutMapping("/quest/{quest_id}")
    public ApplicationResponse<QuestRes> edit(@PathVariable Long quest_id, @ModelAttribute EditQuestReq editQuestReq) {
        return questServiceImpl.edit(quest_id, editQuestReq);
    }

    @ApiOperation(value = "quest 한 개 if로 삭제", notes = "quest id로 삭제.")
    @DeleteMapping("/quest/{quest_id}")
    public ApplicationResponse<Void> deleteById(@PathVariable Long quest_id) {
        return questServiceImpl.deleteById(quest_id);
    }
}
