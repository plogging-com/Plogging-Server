package com.plogging.domain.User.service.userBadge;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.Board.exception.Board.NotFoundBoardException;
import com.plogging.domain.User.BadgeList;
import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.entity.Badge;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.entity.UserBadge;
import com.plogging.domain.User.repository.BadgeRepository;
import com.plogging.domain.User.repository.UserBadgeRepository;
import com.plogging.global.utill.imgae.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBadgeServiceImpl implements UserBadgeService{

    private final BadgeRepository badgeRepository;

    private final UserBadgeRepository userBadgeRepository;

    private final AwsS3Service awsS3Service;

    @Override
    public boolean createBadge(User user, Board board) {

        return false;
    }

    @Override
    public void createBadge(BadgeRequest badgeRequest) {
        System.out.println(badgeRequest.getMultipartFile().getOriginalFilename());

        String fileName = awsS3Service.uploadImage(badgeRequest.getMultipartFile());
        Badge save = badgeRepository.save(Badge.toEntity(badgeRequest , fileName));
        log.info("badge => {}" , save);

    }

    @Override
    public void giveBadgeToUser(BadgeList newBiePhotoGrapher , User user) {

        Badge badge = badgeRepository.findByName(newBiePhotoGrapher.getName()).orElseThrow(NotFoundBoardException::new);

        UserBadge save = userBadgeRepository.save(UserBadge.toEntity(badge, user));

    }
}
