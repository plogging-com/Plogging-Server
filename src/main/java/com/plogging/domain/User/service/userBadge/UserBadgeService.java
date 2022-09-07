package com.plogging.domain.User.service.userBadge;

import com.plogging.domain.User.BadgeList;
import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.dto.response.UserBadgeListRes;
import com.plogging.domain.User.entity.User;
import com.plogging.global.dto.ApplicationResponse;

import java.util.List;

public interface UserBadgeService {


    void createBadge(BadgeRequest badgeRequest);

    void giveBadgeToUser(BadgeList newBiePhotoGrapher , User user);

    ApplicationResponse<Void> changeMainBadge(Long badgeIdx);

    List<UserBadgeListRes> getUserBadgeList();

    void getButton();

    void getFootWork(Long footworkNum);

}
