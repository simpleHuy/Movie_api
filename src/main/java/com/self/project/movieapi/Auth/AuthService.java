package com.self.project.movieapi.Auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.self.project.movieapi.Auth.DTO.*;
import com.self.project.movieapi.User.Model.*;
import com.self.project.movieapi.User.Repository.RefreshTokenRepository;
import com.self.project.movieapi.User.UserService;
import com.self.project.movieapi.Util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;
    private UserService customUserDetailsService;
    private ObjectMapper objectMapper;
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserService customUserDetailsService, ObjectMapper objectMapper, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.objectMapper = objectMapper;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public ResponseEntity<?> login(AuthRequest authRequest) {
        try{
            //attempt auth

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(),
                    authRequest.password()));

            UserModel userDetails = (UserModel) authentication.getPrincipal();
            String jwt = jwtUtil.generateToken(authentication);

            String refreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());
            RefreshToken refreshTokenEntity = new RefreshToken(
                    refreshToken,
                    Date.from(Instant.now().plusMillis(jwtUtil.getRefreshExpirationInMs())),
                    userDetails
            );

            refreshTokenRepository.save(refreshTokenEntity);

            return ResponseEntity.ok(new AuthResponse(jwt, refreshToken));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("Invalid credentials or login error"));
        }
    }

    public ResponseEntity<?> refreshToken(RefreshTokenRequest refreshTokenRequest) {
        try {
            String refreshToken = refreshTokenRequest.refreshToken();
            Optional<RefreshToken> refreshTokenFromDb = refreshTokenRepository.findByToken(refreshToken);

            if(refreshTokenFromDb.isEmpty() || refreshTokenFromDb.get().getExpiryDate().before(new Date()) || refreshTokenFromDb.get().isRevoked()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid refresh token"));
            }

            RefreshToken validRefreshToken = refreshTokenFromDb.get();
            UserModel userDetails = validRefreshToken.getUser();

            String newJwt = jwtUtil.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            String newRefreshToken = jwtUtil.generateRefreshToken(userDetails.getUsername());

            validRefreshToken.setToken(newRefreshToken);
            validRefreshToken.setExpiryDate(Date.from(Instant.now().plusMillis(jwtUtil.getRefreshExpirationInMs())));

            refreshTokenRepository.save(validRefreshToken);

            return ResponseEntity.ok(new AuthResponse(newJwt, newRefreshToken));
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid refresh token"));
        }
    }

    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest) {
        try {
            String authHeader = httpServletRequest.getHeader("Authorization");
            if(authHeader != null && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);
                String username = jwtUtil.getUsernameFromToken(jwt);

                List<RefreshToken> refreshTokens = refreshTokenRepository.findByUser_Username(username);
                for (RefreshToken token : refreshTokens) {
                    token.setRevoked(true);
                }
                refreshTokenRepository.saveAll(refreshTokens);
            }

            return ResponseEntity.ok("Logged out successfully !!");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Logout failed !!"));
        }
    }
}
