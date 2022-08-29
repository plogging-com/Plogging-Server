package com.plogging.domain.Board.entity;

import com.plogging.domain.User.entity.User;
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
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="boardIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL) // createBoard 테스트를 위해 user를 임의 생성 및 영속성 추가해야 하므로 cascade = ALL 해줌, 추후 제거
    @JoinColumn(name="userIdx")
    private User user;

    @OneToMany(mappedBy="board", cascade=ALL)
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy="board", cascade=ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy="board", cascade=ALL)
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy="board", cascade=ALL)
    private List<Inquiry> inquiry = new ArrayList<>();

    @OneToMany(mappedBy="board", cascade=ALL)
    private List<BoardCategory> boardCategories = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade=ALL)
    private List<Photo> photos = new ArrayList<>();

    private String title;
    private String content;
    private LocalDateTime time;
    private String mainPhotoUrl;

    @Enumerated(EnumType.STRING)
    private PresenceStatus status;

    private int heartCnt;
    private int commentCnt;

    public Board (User user, String title, String content, LocalDateTime time){
        this.user = user;
        this.title = title;
        this.content = content;
        this.time = time;
        this.status = PresenceStatus.valueOf("ACTIVE");
        this.heartCnt = 0;
        this.commentCnt = 0;
    }

    public void addHeart(Heart heart){
        this.hearts.add(heart);
    }

    public void addReport(Report report) {
        this.reports.add(report);
    }

    public void changeBoardDelete(){
        this.status = PresenceStatus.valueOf("DELETE");
    }

    public void plusCommentCnt(){
        this.commentCnt++;
    }

    public void plusHeartCnt(){
        this.heartCnt++;
    }


    public void addInquiry(Inquiry inquiry) {
        this.inquiry.add(inquiry);
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
    }

    public void addMainPhotoUrl(String url){
        this.mainPhotoUrl = url;
    }
}
