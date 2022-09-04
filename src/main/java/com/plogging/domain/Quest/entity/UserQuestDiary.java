package com.plogging.domain.Quest.entity;


import com.plogging.domain.Quest.dto.userQuestDiary.request.QuestDiaryReq;
import com.plogging.domain.User.entity.User;
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
public class UserQuestDiary {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userQuestDiaryIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questIdx")
    private Quest quest;

    @OneToMany(mappedBy = "userQuestDiary", cascade=ALL)
    private List<DiaryPhoto> photos = new ArrayList<>();

    private LocalDateTime time;
    private String comment;

    public static UserQuestDiary create(QuestDiaryReq questDiaryReq, List<String> filenames, Quest quest, User user) {
        UserQuestDiary userQuestDiary = new UserQuestDiary();
        userQuestDiary.comment = questDiaryReq.getComment();
        filenames.forEach((f) -> DiaryPhoto.create(userQuestDiary, f));
        userQuestDiary.time = LocalDateTime.now();
        userQuestDiary.addQuest(quest);
        userQuestDiary.addUser(user);
        return userQuestDiary;
    }

    private void addUser(User user){
        this.user = user;
        user.addQuestDiary(this);
    }

    private void addQuest(Quest quest){
        this.quest = quest;
        quest.addQuestDiary(this);
    }

    public void addPhoto(DiaryPhoto diaryPhoto) {
        this.photos.add(diaryPhoto);
    }

    public List<String> getFilenames() {
        ArrayList<String> filenames = new ArrayList<>();
        this.photos.forEach((p) -> filenames.add(p.getFilename()));
        return filenames;
    }
}