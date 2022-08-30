package com.plogging.domain.User.entity;

import com.plogging.domain.Board.entity.Board;
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
public class UserBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userBadgeIdx")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userIdx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="badgeIdx")
    private Badge badge;

    private void addBadge(Badge badge){
        this.badge = badge;
        badge.addBadge(this);
    }

    private void addUser(User user){
        this.user = user;
        user.addBadge(this);
    }

    public static UserBadge toEntity(Badge badge , User user){
        UserBadge userBadge = UserBadge.builder().build();
        userBadge.addUser(user);
        userBadge.addBadge(badge);
        return userBadge;
    }





}
