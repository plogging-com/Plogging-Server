package com.plogging.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BadgeList {

    NewBiePhotoGrapher(1L , "초보사진가");

    private final Long idx;
    private final  String name;

}
