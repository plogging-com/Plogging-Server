package com.plogging.domain.User.service.userBadge;

import com.plogging.domain.Board.entity.Board;
import com.plogging.domain.User.BadgeList;
import com.plogging.domain.User.dto.request.BadgeRequest;
import com.plogging.domain.User.entity.User;

public interface UserBadgeService {


    void createBadge(BadgeRequest badgeRequest);

    void giveBadgeToUser(BadgeList newBiePhotoGrapher , User user);

    void changeMainBadge(Long badgeIdx);

    void getUserBadgeList();


}
