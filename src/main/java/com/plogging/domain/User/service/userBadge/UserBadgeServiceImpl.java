package com.plogging.domain.User.service.userBadge;

import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.User.BadgeList;
import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.dto.response.UserBadgeListRes;
import com.plogging.domain.User.entity.Badge;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.entity.UserBadge;
import com.plogging.domain.User.exception.NotFirstPloggingException;
import com.plogging.domain.User.exception.NotFoundBadgeException;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.exception.ValidUserFindPagingException;
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

        System.out.println(badgeRequest.getMultipartFile() == null);

        String fileName = awsS3Service.uploadImage(badgeRequest.getMultipartFile());
        Badge save = badgeRepository.save(Badge.toEntity(badgeRequest , fileName));
        log.info("badge => {}" , save);

    }

    @Override
    public void giveBadgeToUser(BadgeList newBiePhotoGrapher , User user) {

        Badge badge = badgeRepository.findByName(newBiePhotoGrapher.getName()).orElseThrow(NotFoundBoardException::new);

        UserBadge save = userBadgeRepository.save(UserBadge.toEntity(badge, user));

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
    public void getButton() {

        User user = userRepository.findByLoginId(jwtService.getLoginId()).orElseThrow(NotFoundUserException::new);

        if(!user.getIsFirstPlogging()) {
            Badge isFirstButton = badgeRepository.findByName("처음 시작!").orElseThrow(NotFoundBadgeException::new);

            user.isFirstPlogging();

            UserBadge userBadge = UserBadge.toEntity(isFirstButton, user);

            userBadgeRepository.save(userBadge);

        }else {
            throw new NotFirstPloggingException();
        }

    }

    @Override
    public void getFootWork(Long footworkNum) {

        String footWorkBadgeName ="";
        if (footworkNum >= 10000) {
            footWorkBadgeName = "초보 워커";
        }else if(footworkNum >= 30000) {
            footWorkBadgeName = "중수 워커";
        }else if(footworkNum >= 50000) {
            footWorkBadgeName = "고수 워커";
        }









    }


}
