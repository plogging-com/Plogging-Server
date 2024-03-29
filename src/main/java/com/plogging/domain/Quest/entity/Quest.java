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
    // Pageable.
    // AwsImageService.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="questIdx")
    private Long id;
    private String name;
    private String photo;
    private Integer maxLevel;

    @OneToMany(mappedBy="quest", cascade=ALL)
    private List<UserQuestDiary> userQuestDiaries = new ArrayList<>();

    @OneToMany(mappedBy="quest", cascade=ALL)
    private List<UserQuestProceeding> userQuestProceedings = new ArrayList<>();

    @OneToMany(mappedBy="quest", cascade=ALL)
    private List<UserQuestComplete> userQuestCompletes = new ArrayList<>();

    public Quest(String name, int maxLevel, String photo){
        this.name = name;
        this.maxLevel = maxLevel;
        this.photo = photo;
    }

    public void edit(String name, Integer maxLevel, String photo){
        if(name != null && !name.isEmpty()) this.name = name;
        if(maxLevel != null) this.maxLevel = maxLevel;
        if(photo != null && !photo.isEmpty()) this.photo = photo;
    }

    public void addProceedingQuest(UserQuestProceeding userQuestProceeding) {
        this.userQuestProceedings.add(userQuestProceeding);
    }

    public void addQuestDiary(UserQuestDiary userQuestDiary) {
        this.userQuestDiaries.add(userQuestDiary);
    }
}
// 1. 1km 마다 쓰레기 10개 줍기
// 2. 10일 동안 쓰레기 40개 줍기
