package com.plogging.global.Init;

import com.plogging.domain.User.dto.request.UserJoinReq;
import com.plogging.domain.User.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDB {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAutoConfig;

    private final UserService userService;

    @PostConstruct
    public void initDB() throws IOException {
        if(ddlAutoConfig.equals("create")){

            UserJoinReq userJoinReq1 = new UserJoinReq("plog123" , "plog123!!" , "01012345678" , "plogging_1" , "photo1");

            UserJoinReq userJoinReq2 = new UserJoinReq("plog456" , "plog456!!" , "01067823459" , "plogging_2" , "photo2");


            userService.join(userJoinReq1);
            userService.join(userJoinReq2);

        }
    }



}
