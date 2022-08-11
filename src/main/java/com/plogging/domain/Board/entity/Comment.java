package com.plogging.domain.Board.entity;

import com.plogging.domain.User.entity.User;
import com.plogging.global.enumerations.PresenceStatus;
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
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="commentIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardIdx")
    private Board board;

    @Enumerated(EnumType.STRING)
    private PresenceStatus status;

    private String content;
    private LocalDateTime time;

    private Long groupNum;
    // 댓글일 경우 : null
    // 대댓글일 경우 : 댓글의 "commentIdx"가 들어감

}
