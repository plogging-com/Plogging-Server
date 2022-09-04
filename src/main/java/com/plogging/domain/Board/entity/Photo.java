package com.plogging.domain.Board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="photoIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardIdx")
    private Board board;

    private String fileName;

    @Builder
    public Photo(Board board, String fileName) {
        this.fileName = fileName;

        this.board = board;
        board.addPhoto(this);
    }
}
