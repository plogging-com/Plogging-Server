package com.plogging.domain.User.controller;

import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.dto.response.UserBadgeListRes;
import com.plogging.domain.User.service.userBadge.UserBadgeService;
import com.plogging.global.dto.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value = "필요한 뱃지 생성" , notes = "필요한 뱃지를 생성합니다.")
    public void createBadge(BadgeRequest badgeRequest) {
        userBadgeService.createBadge(badgeRequest);
    }


    /**
     * author 신동민
     * 자신의 뱃지 리스트 확인
     */
    @ApiOperation(value = "자신의 뱃지 리스트 확인" , notes = "자신이 가지고 있는 배지들을 확인합니다.")
    @PostMapping("/getBadgeList")
    public ApplicationResponse<List<UserBadgeListRes>> getBadge() {
        return ApplicationResponse.create("유저뱃지 리스트입니다" , userBadgeService.getUserBadgeList());
    }


    /**
     * author 신동민
     * 메인 뱃지 변경
     */
    @ApiOperation(value = "메인 뱃지 변경" , notes = "뱃지의 idx를 입력하면 해당 뱃지로 메인뱃지를 바굽니다.")
    @PostMapping("/changeBadge/{badgeIdx}")
    public ApplicationResponse<Void> changeBadge(@PathVariable Long badgeIdx) {
        return userBadgeService.changeMainBadge(badgeIdx);
    }

    /**
     * author 신동민
     * 최초 버튼 클릭시 뱃지 부여
     */
    @ApiOperation(value = "버튼 클릭시" , notes = "최초 버튼 클릭시 배지 부여")
    @PostMapping("/button")
    public ApplicationResponse<Void> button() {
        userBadgeService.getButton();
        return null;
    }

    /**
     * author 신동민
     * 특정 발걸음 도달시 특정 배지부여
     */
    @ApiOperation(value = "특정 발걸음 도달시" , notes = "특정 발걸음 도달시에 뱃지를 부여합니다.")
    @PostMapping("/footwork/{footworkNum}")
    public ApplicationResponse<Void> footwork(@PathVariable Long footworkNum) {
        userBadgeService.getFootWork(footworkNum);
        return null;
    }















}
