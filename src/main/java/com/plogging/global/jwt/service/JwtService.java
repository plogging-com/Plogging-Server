package com.plogging.global.jwt.service;

import javax.validation.Valid;

public interface JwtService {


    String createAccessJwt(@Valid String loginId);
    String createRefreshJwt(@Valid String loginId);

    String resolveAccessToken();

    Long resolveRefreshToken();

    String getLoginId();
    Long getUserId();

}
