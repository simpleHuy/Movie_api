package com.self.project.movieapi.User.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity(name = "refresh_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    private Long id;
    private String token;
    private Date expiryDate;
    private boolean revoked;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    public RefreshToken(String token, Date expiryDate, UserModel user) {
        this.token = token;
        this.expiryDate = expiryDate;
        this.user = user;
    }
}
