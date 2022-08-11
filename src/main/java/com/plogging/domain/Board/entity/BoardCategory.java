package com.plogging.domain.Board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="boardCategoryIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardIdx")
    private Board boardIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryIdx")
    private Category category;
}
