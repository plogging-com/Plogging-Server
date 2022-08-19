package com.plogging.domain.Board.entity;

import com.plogging.domain.User.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Report {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reportIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardIdx")
    private Board board;

    private String content;
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    @Builder
    public Report(User user, Board board, String content, LocalDateTime time, ReportStatus status) {
        this.addBoard(board);
        this.addUser(user);

        this.content = content;
        this.time = time;
        this.status = status;
    }

    private void addBoard(Board board){
        this.board = board;
        board.addReport(this);
    }

    private void addUser(User user){
        this.user = user;
        user.addReport(this);
    }

    public Report editStatus(ReportStatus status) {
        this.status = status;
        return this;
    }

    public void changeContent(String newContent) {
        this.content = newContent;
    }
}
