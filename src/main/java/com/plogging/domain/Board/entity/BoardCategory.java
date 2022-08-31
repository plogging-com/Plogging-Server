package com.plogging.domain.Board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BoardCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="boardCategoryIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardIdx")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryIdx")
    private Category category;

    @Builder
    public BoardCategory(Board board, Category category){
        this.addBoard(board);
        this.addCategory(category);
    }

    public void addBoard(Board board){
        this.board = board;
        board.addBoardCategory(this);
    }

    public void addCategory(Category category){
        this.category = category;
        category.addBoardCategory(this);
    }

}
