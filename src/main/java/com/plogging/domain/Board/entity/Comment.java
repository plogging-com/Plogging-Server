package com.plogging.domain.Board.entity;

import com.plogging.domain.User.entity.User;
import com.plogging.global.enumerations.PresenceStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    @Builder
    public Comment(User user, Board board, PresenceStatus status, String content, LocalDateTime time, Long groupNum){
        this.user = user;
        this.board = board;
        this.content = content;
        this.time = time;
        this.groupNum = groupNum;
    }

    public void changeCommentDelete() {
        this.status = PresenceStatus.DELETE;
    }

    public void changeContent(String newContent) {
        this.content = newContent;
    }
    // 댓글일 경우 : null
    // 대댓글일 경우 : 댓글의 "commentIdx"가 들어감
}
