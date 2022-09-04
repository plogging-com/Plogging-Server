package com.plogging.domain.Quest.controller;

import com.plogging.domain.Quest.dto.userQuestProceeding.response.QuestProceedingDetailRes;
import com.plogging.domain.Quest.service.questProceeding.QuestProceedingService;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.UserIDValidException;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.domain.User.service.user.UserService;
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
@RequestMapping("api/quests-proceeding")
@Api(tags = "Quest API")
public class QuestProceedingController {

    private final QuestProceedingService questProceedingService;

    @ApiOperation(value = "진행중인 quest 전체 조회", notes = "진행중 quest 전체 조회.")
    @GetMapping("/")
    public ApplicationResponse<Page<QuestProceedingDetailRes>> findAll(Pageable pageable){
        return questProceedingService.findAll(pageable);
    }

    @ApiOperation(value = "진행중인 quest 한 개 id로 조회", notes = "진행중 quest 조회.")
    @GetMapping("/{quest_proceeding_id}")
    public ApplicationResponse<QuestProceedingDetailRes> findById(@PathVariable Long quest_proceeding_id){
        return questProceedingService.findById(quest_proceeding_id);
    }

    @ApiOperation(value = "진행중인 quest 한 개 gage 상승시키기", notes = "quest 한 개 gage 상승.")
    @PutMapping("/{quest_procedding_id}/gageup/{value}")
    public ApplicationResponse<Void> gageUp(@PathVariable Long quest_procedding_id, @PathVariable Integer value) {
        return questProceedingService.gageUp(quest_procedding_id, value);
    }
}
