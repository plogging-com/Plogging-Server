package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.quest.request.CreateQuestReq;
import com.plogging.domain.Quest.dto.quest.request.EditQuestReq;
import com.plogging.domain.Quest.dto.quest.response.QuestRes;
import com.plogging.domain.Quest.dto.userQuestComplete.response.QuestCompRes;
import com.plogging.domain.Quest.service.quest.QuestService;
import com.plogging.domain.Quest.service.questComplete.QuestCompleteService;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.UserIdDuplicationException;
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
@RequestMapping("api/quests-complete")
@Api(tags = "Quest API")
public class QuestCompleteController{

    private final QuestCompleteService questCompleteService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @ApiOperation(value = "모든 완료된 quest 목록 조회", notes = "quest 전체 조회.")
    @GetMapping("/")
    public ApplicationResponse<Page<QuestCompRes>> findAll(Pageable pageable){
        return questCompleteService.findAll(pageable);
    }
}