package com.plogging.domain.User.service.userBadge;

import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.User.BadgeList;
import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.dto.response.UserBadgeCreateRes;
import com.plogging.domain.User.dto.response.UserBadgeListRes;
import com.plogging.domain.User.entity.Badge;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.entity.UserBadge;
import com.plogging.domain.User.exception.NotFoundBadgeException;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.BadgeRepository;
import com.plogging.domain.User.repository.UserBadgeRepository;
import com.plogging.domain.User.repository.UserRepository;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.jwt.service.JwtService;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBadgeServiceImpl implements UserBadgeService{

    private final BadgeRepository badgeRepository;

    private final UserRepository userRepository;

    private final UserBadgeRepository userBadgeRepository;

    private final JwtService jwtService;

    private final AwsS3Service awsS3Service;


    @Override
    public void createBadge(BadgeRequest badgeRequest) {


        String fileName = awsS3Service.uploadImage(badgeRequest.getMultipartFile());
        Badge save = badgeRepository.save(Badge.toEntity(badgeRequest , fileName));
        log.info("badge => {}" , save);

    }


    @Override
    @Transactional
    public ApplicationResponse<Void> changeMainBadge(Long badgeIdx) {

        Badge badge = badgeRepository.findById(badgeIdx).orElseThrow(NotFoundBoardException::new);

        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new);

        UserBadge cuUserBadge = userBadgeRepository.findByUserAndBadge(user, badge).orElseThrow(NotFoundBoardException::new);

        UserBadge exUserBadge = userBadgeRepository.findByUserAndIsMainBadge(user, true).orElseThrow(NotFoundBoardException::new);

        exUserBadge.setMainBadge(false);
        cuUserBadge.setMainBadge(true);

        return ApplicationResponse.ok();

    }

    @Override
    public  List<UserBadgeListRes> getUserBadgeList() {
        List<UserBadge> userBadgeList = userBadgeRepository.findByUser(userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new));

        if(userBadgeList.isEmpty()) throw new NotFoundUserException();

        List<UserBadgeListRes> result = new ArrayList<>();

        for (UserBadge userBadge : userBadgeList) result.add(UserBadgeListRes.create(userBadge.getBadge()));

        return result;

}

    @Override
    @Transactional
    public ApplicationResponse<UserBadgeCreateRes> startWalking(Long walkingNum) {
        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new);

        user.addWalkingCount(walkingNum);

        Badge badge = badgeRepository.findByName(getWalkingBadgeName(user.getWalkingCount())).orElseThrow(NotFoundBadgeException::new);

        return getUserBadge(user, badge);

    }




    @Override
    @Transactional
    public ApplicationResponse<UserBadgeCreateRes> startPlogging() {

        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new);

        user.addButtonCount();

        Long buttonCount = user.getButtonCount();
        Badge badge = badgeRepository.findByName(getButtonBadgeName(buttonCount)).orElseThrow(NotFoundBadgeException::new);

        return getUserBadge(user, badge);

    }

    private String getButtonBadgeName(Long buttonCount) {
        String buttonBadgeName ="";
        if (buttonCount >= 40) {
            buttonBadgeName = BadgeList.날아라플로깅.name();
        }else if(buttonCount >= 25) {
            buttonBadgeName = BadgeList.뛰어라플로깅.name();
        }else if(buttonCount >= 10) {
            buttonBadgeName = BadgeList.달려라플로깅.name();
        } else if(buttonCount >= 1) {
            buttonBadgeName = BadgeList.첫걸음.name();
        }

        return buttonBadgeName;
    }



    private String getWalkingBadgeName(Long footworkNum) {
        String footWorkBadgeName ="";
        if (footworkNum >= 700000) {
            footWorkBadgeName = BadgeList.세계일주.name();
        }else if(footworkNum >= 500000) {
            footWorkBadgeName = BadgeList.국토대장정.name();
        }else if(footworkNum >= 300000) {
            footWorkBadgeName = BadgeList.동네한바퀴.name();
        } else if(footworkNum >= 100000) {
            footWorkBadgeName = BadgeList.운동장한바퀴.name();
        }
        return footWorkBadgeName;
    }

    private ApplicationResponse<UserBadgeCreateRes> getUserBadge(User user, Badge badge) {
        if(userBadgeRepository.findByUserAndBadge(user, badge).isEmpty()) {
            userBadgeRepository.save(UserBadge.toEntity(badge, user));
            return ApplicationResponse.create("새로운 뱃지 달성!", UserBadgeCreateRes.create(user, badge));
        }else {
            return ApplicationResponse.create("이미 존재하는 뱃지입니다.", UserBadgeCreateRes.create(user, badge));
        }
    }


}
