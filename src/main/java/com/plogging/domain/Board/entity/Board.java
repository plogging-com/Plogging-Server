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
import java.util.Optional;

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

    @ManyToOne(fetch = FetchType.LAZY)
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

    private String title;
    private String content;
    private LocalDateTime time;
    private String photo;

    @Enumerated(EnumType.STRING)
    private PresenceStatus status;

    public Board (User user, String title, String content, LocalDateTime time, String photo, PresenceStatus status){
        this.user = user;
        this.title = title;
        this.content = content;
        this.time = time;
        this.photo = photo;
        this.status = status;
    }

}
