package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.QuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.service.QuestService;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestController {

    private final QuestService questService;

    @PostMapping("/quest")
    public ApplicationResponse<QuestRes> questCreate(@ModelAttribute QuestReq questReq) {
        return questService.questCreate(questReq);
    }
//    @GettMapping("/quest")
//    @PutMapping("/quest")
//    @DeleteMapping("/quest")



}
