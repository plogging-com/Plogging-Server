package com.plogging.global.interceptor;

import com.plogging.global.jwt.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ArrayList<String> exceptApi = new ArrayList<>();
        exceptApi.add("/api/v1/users/check-nickname");
        exceptApi.add("/api/v1/users/check-id");
        exceptApi.add("/api/v1/users/join");
        exceptApi.add("/api/v1/users/terms");
        exceptApi.add("/api/v1/sms/send");


        for (String s : exceptApi) {
            if(request.getRequestURI().equals(s)) return true;
        }
        jwtService.getLoginId();
        return true;
    }
}
