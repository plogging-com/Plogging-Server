package com.plogging.domain.User.entity;

import com.plogging.domain.Board.entity.*;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
import com.plogging.domain.User.dto.request.UserJoinReq;
import com.plogging.domain.User.dto.request.UserUpdateReq;
import com.plogging.global.enumerations.PresenceStatus;
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_idx")
    private Long id;
    private String loginId;
    private String password;
    private String nickName;
    private String phone;
    private String photo;
    private int growth;
    private LocalDateTime signUpDate;
    private Long level;

    private Long buttonCount;

    private Long walkingCount;

    @Enumerated(EnumType.STRING)
    private PresenceStatus status;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<UserQuestDiary> userQuestDiaries = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<UserQuestProceeding> userQuestProceedings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<UserQuestComplete> userQuestCompletes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Inquiry> inquiry = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = ALL, fetch = FetchType.LAZY)
    private UserRefreshToken userRefreshToken;

    public void updateUser(UserUpdateReq userUpdateReq , String photoUrl) {
        this.nickName = userUpdateReq.getNickname();
        this.photo = photoUrl;
    }


    public void addProceedingQuest(UserQuestProceeding userQuestProceeding) {
        if(this.getUserQuestProceedings() == null) this.userQuestProceedings = new ArrayList<>();
        this.userQuestProceedings.add(userQuestProceeding);
    }

    public void changeUserDelete(){
        this.status = PresenceStatus.DELETE;
    }

    public void setUserRefreshToken(UserRefreshToken userRefreshToken){
        this.userRefreshToken = userRefreshToken;
    }

    public void addHeart(Heart heart){
        this.hearts.add(heart);
    }

    public void addReport(Report report) {
        this.reports.add(report);
    }

    public void addInquiry(Inquiry inquiry) {
        this.inquiry.add(inquiry);
    }

    public void addQuestDiary(UserQuestDiary userQuestDiary) {
        this.userQuestDiaries.add(userQuestDiary);
    }

        public void levelUp() {
            if(this.growth >= 100){
                this.level += 1;
            }else {
                this.growth += 5;
            }
        }

        public void addButtonCount() {

        this.buttonCount++;

    }

    public void addWalkingCount(Long walkingNum) {
        this.walkingCount = this.walkingCount + walkingNum;
    }

    public void initWalkingCount() {
        this.walkingCount = 0L;
    }


    public void addBadge(UserBadge userBadge) {
        this.userBadges.add(userBadge);
    }

    public void changePw(String password){
        this.password = password;
    }
}

