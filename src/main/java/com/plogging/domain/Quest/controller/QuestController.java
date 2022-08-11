package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.service.quest.QuestServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
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

    @PostMapping("/quest")
    public ApplicationResponse<QuestRes> create(@ModelAttribute CreateQuestReq createQuestReq) {
        return questServiceImpl.create(createQuestReq);
    }

    @GetMapping("/quest/{quest_id}")
    public ApplicationResponse<QuestRes> findById(@PathVariable Long quest_id) {
        return questServiceImpl.findById(quest_id);
    }

    @GetMapping("/quest")
    public ApplicationResponse<Page<QuestRes>> findAll(Pageable pageable) {
        return questServiceImpl.findAll(pageable);
    }

    @PutMapping("/quest/{quest_id}")
    public ApplicationResponse<QuestRes> edit(@PathVariable Long quest_id, @ModelAttribute EditQuestReq editQuestReq) {
        return questServiceImpl.edit(quest_id, editQuestReq);
    }

    @DeleteMapping("/quest/{quest_id}")
    public ApplicationResponse<Void> deleteById(@PathVariable Long quest_id) {
        return questServiceImpl.deleteById(quest_id);
    }
}
