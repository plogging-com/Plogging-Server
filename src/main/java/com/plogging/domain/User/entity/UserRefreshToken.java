package com.plogging.domain.User.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRefreshTokenIdx;
    private String refreshToken;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    public void setUser(User user) {
        this.user = user;
        user.setUserRefreshToken(this);
    }

    public void setUserRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
