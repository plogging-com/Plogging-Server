package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.service.quest.QuestServiceImpl;
import com.plogging.domain.User.exception.UserIdDuplicationException;
import com.plogging.global.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestController {

    private final QuestServiceImpl questServiceImpl;

    @GetMapping("/quest")
    public void find() {
        throw new UserIdDuplicationException();
    }

    @PostMapping("/quest")
    public ApplicationResponse<QuestRes> questCreate(@ModelAttribute CreateQuestReq createQuestReq) {
        return questServiceImpl.create(createQuestReq);
    }


//    @GettMapping("/quest")
//    @PutMapping("/quest")
//    @DeleteMapping("/quest")



}
