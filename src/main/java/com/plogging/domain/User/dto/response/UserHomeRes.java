package com.plogging.domain.User.dto.response;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "홈 화면 응답 객체")
public class UserHomeRes {

    private Long today;
    private int level;
    private int growth;
    private String nickName;
    private int step;
    private int time;

    private String QuestPhoto;
    private String todayQuest;
}
