package com.plogging.domain.Quest.entity;

import com.plogging.domain.Board.entity.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DiaryPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="diaryPhotoIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userQuestDiaryIdx")
    private UserQuestDiary userQuestDiary;

    private String filename;

    @Builder
    public DiaryPhoto(UserQuestDiary userQuestDiary, String filename){
        this.filename = filename;
        this.userQuestDiary = userQuestDiary;
        userQuestDiary.addPhoto(this);
    }

    public static  DiaryPhoto create(UserQuestDiary userQuestDiary, String filename){
        DiaryPhoto diaryPhoto = new DiaryPhoto();
        diaryPhoto.filename = filename;
        diaryPhoto.userQuestDiary = userQuestDiary;
        userQuestDiary.addPhoto(diaryPhoto);
        return diaryPhoto;
    }
}
