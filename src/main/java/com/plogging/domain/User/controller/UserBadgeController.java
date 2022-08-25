package com.plogging.domain.User.controller;

import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.service.userBadge.UserBadgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/userBadge")
@Api(tags = "User Badge API")
public class UserBadgeController {

    private final UserBadgeService userBadgeService;


    /**
     * 필요한 뱃지생성
     * author 신동민
     */
    @PostMapping("/create")
    public String createBadge(BadgeRequest badgeRequest) {
        userBadgeService.createBadge(badgeRequest);
        return null;
    }

    @ApiOperation(value = "자신의 뱃지 확인" , notes = "자신이 가지고 있는 배지를 확인합니다.")
    @PostMapping("/get/{userIdx}")
    public String getBadge(@PathVariable Long userIdx) {
        return null;
    }









}
