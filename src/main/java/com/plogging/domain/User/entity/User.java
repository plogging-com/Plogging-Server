package com.plogging.domain.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plogging.domain.Board.entity.*;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.User.dto.UserJoinReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_idx")
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private String nickName;
    private String phone;
    private String photo;
    private String growth;
    private LocalDateTime signUpDate;
    private int level;

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<UserBadge> userBadges;

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<UserQuestDiary> userQuestDiaries = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<UserQuestProceeding> userQuestProceedings = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<UserQuestComplete> userQuestCompletes = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy="user", cascade=ALL)
    private List<Inquiry> inquiry = new ArrayList<>();

    @OneToOne(mappedBy = "user" , cascade = ALL , fetch = FetchType.LAZY)
    private UserRefreshToken userRefreshToken;

    public static User toEntity(UserJoinReq userJoinReq) {
        return User.builder()
                .loginId(userJoinReq.getId())
                .password(userJoinReq.getPassword())
                .name(userJoinReq.getName())
                .nickName(userJoinReq.getNickname())
                .phone(userJoinReq.getPhone())
                .photo(userJoinReq.getPhoto())
                .signUpDate(LocalDateTime.now())
                .level(1).build();
    }


    public void setUserRefreshToken(UserRefreshToken userRefreshToken) {
        this.userRefreshToken = userRefreshToken;
    }
}
