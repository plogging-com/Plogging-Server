package com.plogging.domain.Quest.entity;


import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuestDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userQuestDiaryIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questIdx")
    private Quest quest;

    private LocalDateTime time;
    private String comment;
    private String photo;

}
