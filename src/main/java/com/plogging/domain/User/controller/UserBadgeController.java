package com.plogging.domain.User.controller;

import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.dto.response.UserBadgeCreateRes;
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
     * 발걸음수 추가하기 및 그에따른 뱃지부여
     */
    @ApiOperation(value = "발걸음수 추가하기 및 그에따른 뱃지부여" , notes = "발걸음수를 저장하고 특정 조건 달성시에 그에 걸맞는 뱃지를 부여하고 그값을 리턴합니다.")
    @PostMapping("/addWalkNum/{walkingNum}")
    public ApplicationResponse<UserBadgeCreateRes> addWalkNum(@PathVariable Long walkingNum) {
        return userBadgeService.startWalking(walkingNum);
    }

    /**
     * author 신동민
     * 플로깅 버튼을 누르고 플로깅 버튼을 누른수를 카운트하여 그에따른 뱃지를 부여합니다.
     */
    @ApiOperation(value = "플로깅 버튼 누르기 및 뱃지부여" , notes = "플로깅 버튼누르면 플로깅 버튼누른 만큼 카운트 됩니다.")
    @PostMapping("/startPlogging")
    public ApplicationResponse<UserBadgeCreateRes> startPlogging() {
       return userBadgeService.startPlogging();
    }
















}
