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

    @OneToMany(mappedBy="board", cascade=ALL)
    private List<BoardCategory> boardCategories;

    private String title;
    private String content;
    private LocalDateTime time;
    private String photo;

    @Enumerated(EnumType.STRING)
    private PresenceStatus status;

}
