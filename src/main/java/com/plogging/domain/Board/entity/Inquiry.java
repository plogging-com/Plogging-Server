package com.plogging.domain.Board.entity;

import com.plogging.domain.User.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="inquiryIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardIdx")
    private Board board;

    @Builder
    public Inquiry(User user, Board board) {
        this.addUser(user);
        this.addBoard(board);
    }

    private void addBoard(Board board) {
        this.board = board;
        board.addInquiry(this);
    }

    private void addUser(User user) {
        this.user = user;
        user.addInquiry(this);
    }


}
