package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config.AppConst;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.enums.Role;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.AuthorizationHeaderException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider.JwtAccessTokenProvider;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtAccessTokenProviderImpl implements JwtAccessTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.access-token-expiration-millis}")
    private long jwtAccessTokenExpirationMillis;

    @Override
    public String generateToken(UserResponse userResponse) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, userResponse.getUsername());
        claims.put(AppConst.ROLES_CLAIM, userResponse.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toList()));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(jwtAccessTokenExpirationMillis)))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @Override
    public UserResponse userInfoByToken(String accessToken) {
        Claims claims = parseJwtToken(accessToken);
        return UserResponse.builder()
                .username(claims.getSubject())
                .roles(((List<String>) claims.get(AppConst.ROLES_CLAIM)).stream()
                        .map(Role::valueOf)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public Instant getExpiration(String accessToken) {
        return parseJwtToken(accessToken).getExpiration().toInstant();
    }

    private Claims parseJwtToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException exception) {
            throw new AuthorizationHeaderException("Token expired");
        }
    }
}