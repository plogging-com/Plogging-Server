package com.plogging.domain.User.service.user;

import com.plogging.domain.User.dto.request.*;
import com.plogging.domain.User.dto.response.UserFindRes;
import com.plogging.domain.User.dto.response.UserJoinRes;
import com.plogging.domain.User.dto.response.UserLoginRes;
import com.plogging.domain.User.dto.response.UserUpdateFormRes;
import com.plogging.domain.User.entity.User;
import com.plogging.domain.User.exception.NotFoundUserException;
import com.plogging.domain.User.exception.ValidUserFindPagingException;
import com.plogging.domain.User.repository.UserRepository;
//import com.plogging.global.jwt.service.JwtService;
import com.plogging.domain.User.service.userToken.UserRefreshTokenService;
import com.plogging.global.dto.ApplicationResponse;
import com.plogging.global.jwt.service.JwtService;
import com.plogging.global.utill.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserRefreshTokenService userRefreshTokenService;


    @Override
    @Transactional
    public UserJoinRes join(UserJoinReq userJoinReq) {

        userJoinReq.setPassword(SHA256Util.encrypt(userJoinReq.getPassword()));

        String name = userRepository.save(User.toEntity(userJoinReq)).getNickName();

        return UserJoinRes.builder().username(name).build();
    }

    @Override
    @Transactional
    public ApplicationResponse<Void> delete(UserDeleteReq userDeleteReq) {

        User user = userRepository.findById(userDeleteReq.getUserIdx()).orElseThrow(NotFoundUserException::new);
        user.changeUserDelete();
        return ApplicationResponse.ok();

}

    @Override
    @Transactional
    public UserLoginRes login(UserLoginReq userLoginReq) {

        User user = userRepository.findByLoginId(userLoginReq.getLoginId()).orElseThrow(NotFoundUserException::new);

        if(!user.getPassword().equals(SHA256Util.encrypt(userLoginReq.getPassword())))throw new NotFoundUserException();

        String accessJwt = jwtService.createAccessJwt(user.getLoginId());
        String refreshJwt = jwtService.createRefreshJwt(user.getLoginId());
        long refreshTokenIdx = userRefreshTokenService.insertRefreshToken(refreshJwt, user);

        return UserLoginRes.from(user , accessJwt , refreshTokenIdx);
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

    @Override
    public List<UserFindRes> findUser(UserFindReq userFindReq) {

        if (userFindReq.getPg() <=0 || userFindReq.getPg() > 15 || userFindReq.getSz() <= 0 || userFindReq.getUserNickName() == null || userFindReq.getUserNickName().isEmpty()) throw new ValidUserFindPagingException();

        List<User> users = userRepository.findUserName(userFindReq);

        if (users.isEmpty()) throw new NotFoundUserException();

        List<UserFindRes> result = new ArrayList<>();

        for (User user : users) result.add(UserFindRes.from(user));

        return result;
    }

    @Override
    @Transactional
    public UserUpdateFormRes getUpdateForm(UserUpdateFormReq userUpdateFormReq) {

        return UserUpdateFormRes.create(userRepository.findById(userUpdateFormReq.getUserIdx()).orElseThrow(NotFoundUserException::new));

    }

    @Override
    @Transactional
    public ApplicationResponse<Void> update(UserUpdateReq userUpdateReq) {

        User user = userRepository.findById(userUpdateReq.getUserIdx()).orElseThrow(NotFoundUserException::new);

        user.updateUser(userUpdateReq);

        return ApplicationResponse.ok();

    }
}