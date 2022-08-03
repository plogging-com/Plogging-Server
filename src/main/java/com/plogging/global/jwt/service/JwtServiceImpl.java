package com.plogging.global.jwt.service;

import com.plogging.domain.User.entity.UserRefreshToken;
import com.plogging.domain.User.repository.UserRefreshTokenRepository;
import com.plogging.global.jwt.exception.ExpireAccessException;
import com.plogging.global.jwt.exception.NotFoundJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;



@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtServiceImpl implements JwtService{

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 2 * 1000L;

    private final long REFRESH_TOKEN_VALID_TIME  = 60 * 60 * 24 * 7 * 1000L;

//    @Value("${spring.jwt.access-key}")
    private String JWT_ACCESS_SECRET_KEY="test";

//    @Value("${spring.jwt.refresh-key}")
    private String JWT_REFRESH_SECRET_KEY="test";

    private Long userId;


    @Override
    public String createAccessJwt(String loginId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("loginId", loginId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, JWT_ACCESS_SECRET_KEY)
                .compact();
    }

    @Override
    public String createRefreshJwt(String loginId) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("loginId", loginId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, JWT_REFRESH_SECRET_KEY)
                .compact();
    }

    @Override
    public String resolveAccessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader("X-ACCESS-TOKEN");
    }

    @Override
    public Long resolveRefreshToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return Long.valueOf(request.getHeader("X-REFRESH-TOKEN"));
    }

    @Override
    public String getLoginId() {
        //JWT 추출합니다.
        String accessToken = resolveAccessToken();
        if (accessToken == null || accessToken.length() == 0)  throw new NotFoundJwtException();

        Long refreshToken = resolveRefreshToken();
        if (refreshToken == null )  throw new NotFoundJwtException();

        Jws<Claims> claims;

        try{
            claims = Jwts.parser()
                    .setSigningKey(JWT_ACCESS_SECRET_KEY)
                    .parseClaimsJws(accessToken); // 파싱 및 검증, 실패 시 에러
        }catch (Exception e3){
            throw new ExpireAccessException();
        }

        // JWT REFRESH parsing합니다.
        try{
////            UserRefreshToken userRefreshTokenRepo = userRefreshTokenRepository.findByUserUserId(refreshToken);
//            String userRefreshToken = userRefreshTokenRepo.getRefreshToken();
//            userId = userRefreshTokenRepo.getUser().getId();
            claims = Jwts.parser()
                    .setSigningKey(JWT_REFRESH_SECRET_KEY)
                    .parseClaimsJws("1234");  // 파싱 및 검증, 실패 시 에러
        }catch (Exception e2){
            throw new ExpireAccessException();
        }
        return claims.getBody().get("loginId",String.class);
    }

    @Override
    public Long getUserId() {
        return null;
    }
}
