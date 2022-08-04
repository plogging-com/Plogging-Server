package com.plogging.domain.User.service;

import com.plogging.domain.User.dto.request.UserDeleteReq;
import com.plogging.domain.User.dto.request.UserJoinReq;
import com.plogging.domain.User.dto.request.UserLoginReq;
import com.plogging.domain.User.dto.response.UserJoinRes;
import com.plogging.domain.User.dto.response.UserLoginRes;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.repository.UserRepository;
//import com.plogging.global.jwt.service.JwtService;
import com.plogging.global.utill.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final JwtService jwtService;

//    private final UserRefreshTokenService userRefreshTokenService;


    @Override
    public UserJoinRes join(UserJoinReq userJoinReq) {

        userJoinReq.setPassword(SHA256Util.encrypt(userJoinReq.getPassword()));

        String name = userRepository.save(User.toEntity(userJoinReq)).getNickName();

        return UserJoinRes.builder().username(name).build();
    }

    @Override
    public User delete(UserDeleteReq userDeleteReq) {
        return null;
    }

    @Override
    public UserLoginRes login(UserLoginReq userLoginReq) {

        User user = userRepository.findByLoginId(userLoginReq.getLoginId()).orElseThrow(NotFoundUserException::new);

//        String accessJwt = jwtService.createAccessJwt(user.getLoginId());
//        String refreshJwt = jwtService.createRefreshJwt(user.getLoginId());
//
//        long refreshTokenIdx = userRefreshTokenService.insertRefreshToken(refreshJwt, user);

//        return UserLoginRes.builder()
//                .accessToken(accessJwt)
//                .refreshTokenIdx(refreshTokenIdx).build();
        return null;
    }

    @Override
    public String checNickname(String nickName) {
        if (userRepository.findByNickName(nickName).isEmpty()) {
            if (Pattern.matches("^[a-z0-9가-힣]{2,5}$", nickName)) {
                return null;
            }else {
                return "닉네임은 2~5글자의 영소문자, 숫자, 한글만 가능합니다.";
            }
        } else {
            return "중복된 닉네임입니다.";
        }
    }

    @Override
    public String checkLoginId(String loginId) {
        if (userRepository.findByLoginId(loginId).isEmpty()) {
            if (Pattern.matches("^.*(?=^.{8,20}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", loginId)) {
                return null;
            } else {
                return "로그인 Id는 6~18글자의 영소문자, 숫자만 가능합니다.";
            }
        } else {
            return "중복된 아이디입니다.";
        }
    }
}