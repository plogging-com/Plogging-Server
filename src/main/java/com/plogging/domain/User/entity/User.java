package com.plogging.domain.User.entity;

import com.plogging.domain.Board.entity.Comment;
import com.plogging.domain.Board.entity.Inquiry;
import com.plogging.domain.Board.entity.Heart;
import com.plogging.domain.Board.entity.Report;
import com.plogging.domain.Quest.entity.UserQuestComplete;
import com.plogging.domain.Quest.entity.UserQuestDiary;
import com.plogging.domain.Quest.entity.UserQuestProceeding;
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
    @Column(name="userIdx")
    private Long id;

    private String password;
    private String name;
    private String nickName;
    private String phone;
    private String photo;
    private String growth;

    private LocalDateTime signUpDate;

    private int level;

    private Long mainBadge; // badge_idx (pk)

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
}
