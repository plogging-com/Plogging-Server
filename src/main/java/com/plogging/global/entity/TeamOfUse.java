package com.plogging.global.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamOfUse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_of_useridx")
    private Long id;

    private String name;
    private String value;


}
