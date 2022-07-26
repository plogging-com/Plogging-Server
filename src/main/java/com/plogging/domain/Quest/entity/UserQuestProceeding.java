package com.plogging.domain.Quest.entity;


import com.plogging.domain.User.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuestProceeding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userQuestProceedingIdx")
    private Long id;

    private int level;
    private int gage;
    private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questIdx")
    private Quest quest;


}
