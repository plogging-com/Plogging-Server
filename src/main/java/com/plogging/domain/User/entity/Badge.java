package com.plogging.domain.User.entity;

import com.plogging.domain.User.dto.request.BadgeRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="badgeIdx")
    private Long id;
    private String name;
    private String photo;
    private String comment;

    public static Badge toEntity(BadgeRequest badgeRequest , String fileName) {
       return Badge.builder().comment(badgeRequest.getComment()).name(badgeRequest.getName()).photo(fileName).build();
    }

    @OneToMany(mappedBy = "badge", cascade = ALL)
    private List<UserBadge> userBadges = new ArrayList<>();

    public void addBadge(UserBadge userBadge) {
        userBadges.add(userBadge);
    }
}
