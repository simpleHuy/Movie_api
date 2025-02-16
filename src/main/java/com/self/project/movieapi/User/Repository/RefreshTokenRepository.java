package com.self.project.movieapi.User.Repository;

import com.self.project.movieapi.User.Model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String refreshToken);

    List<RefreshToken> findByUser_Username(String username);
}
