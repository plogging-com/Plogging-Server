package com.plogging.domain.Board.entity;

import com.plogging.domain.User.entity.User;
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
    private String status;
}
