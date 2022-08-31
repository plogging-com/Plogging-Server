package com.plogging.domain.Board.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="categoryIdx")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @OneToMany(mappedBy="board", cascade=ALL)
    private List<BoardCategory> boardCategories = new ArrayList<>();

    @Builder
    public Category(CategoryName categoryName){
        this.name = categoryName;
    }

    public void addBoardCategory(BoardCategory boardCategory) {
        this.boardCategories.add(boardCategory);
    }
}
