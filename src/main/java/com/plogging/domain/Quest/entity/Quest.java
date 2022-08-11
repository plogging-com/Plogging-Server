package com.plogging.domain.Quest.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="questIdx")
    private Long id;
    private String name;
    private String photo;

    @OneToMany(mappedBy="quest", cascade=ALL)
    private List<UserQuestDiary> userQuestDiaries = new ArrayList<>();

    @OneToMany(mappedBy="quest", cascade=ALL)
    private List<UserQuestProceeding> userQuestProceedings = new ArrayList<>();

    @OneToMany(mappedBy="quest", cascade=ALL)
    private List<UserQuestComplete> userQuestCompletes = new ArrayList<>();

    public Quest(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public void edit(String name, String photoURL) {
        if(name != null && !name.isEmpty()) this.name = name;
        if(photoURL != null && !photoURL.isEmpty()) this.photo = photoURL;
    }

    public void addProceedingQuest(UserQuestProceeding userQuestProceeding) {
        this.userQuestProceedings.add(userQuestProceeding);
    }
}
// 1. 1km 마다 쓰레기 10개 줍기
// 2. 10일 동안 쓰레기 40개 줍기
