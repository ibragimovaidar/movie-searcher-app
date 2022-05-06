package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider;

import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;

import java.time.Instant;

public interface JwtAccessTokenProvider {

    String generateToken(UserResponse userResponse);

    UserResponse userInfoByToken(String accessToken);

    Instant getExpiration(String accessToken);
}
