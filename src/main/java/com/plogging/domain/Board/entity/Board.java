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
    private List<BoardCategory> boardCategories;

    private String title;
    private String content;
    private LocalDateTime time;
    private String photo;

    @Enumerated(EnumType.STRING)
    private PresenceStatus status;

    public Board (User user, String title, String content, LocalDateTime time, String photo){
        this.user = user;
        this.title = title;
        this.content = content;
        this.time = time;
        this.photo = photo;
        this.status = PresenceStatus.valueOf("ACTIVE");
    }

    public void changeBoardDelete(){
        this.status = PresenceStatus.valueOf("DELETE");
    }

}
