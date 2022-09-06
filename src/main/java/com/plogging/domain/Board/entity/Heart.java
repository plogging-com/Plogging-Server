package com.plogging.domain.Board.entity;

import com.plogging.domain.User.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="heartIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardIdx")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @Builder
    public Heart(Board board, User user){
        this.addBoard(board);
        this.addUser(user);
    }

    private void addBoard(Board board){
        this.board = board;
        board.addHeart(this);
    }

    private void addUser(User user){
        this.user = user;
        user.addHeart(this);
    }
}
