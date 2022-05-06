package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.jwt.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.TokenCoupleDto;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider.JwtAccessTokenProvider;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.jwt.JwtTokenService;

@RequiredArgsConstructor
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    @Override
    public TokenCoupleResponse generateToken(UserResponse userResponse) {
        return TokenCoupleResponse.builder()
                .accessToken(jwtAccessTokenProvider.generateToken(userResponse))
                .build();
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto) {
        throw new RuntimeException("Not implemented");
    }
}
