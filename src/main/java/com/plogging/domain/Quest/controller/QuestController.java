package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.service.QuestServiceImpl;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestController {

    private final QuestServiceImpl questServiceImpl;

    @PostMapping("/quest")
    public ApplicationResponse<QuestRes> questCreate(@ModelAttribute CreateQuestReq createQuestReq) {
        return questServiceImpl.questCreate(createQuestReq);
    }


//    @GettMapping("/quest")
//    @PutMapping("/quest")
//    @DeleteMapping("/quest")



}
