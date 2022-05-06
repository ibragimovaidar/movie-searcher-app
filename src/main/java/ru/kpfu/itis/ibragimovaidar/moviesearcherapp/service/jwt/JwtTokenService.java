package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.jwt;

import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.TokenCoupleDto;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;

public interface JwtTokenService {

    TokenCoupleResponse generateToken(UserResponse userResponse);

    TokenCoupleResponse refreshAccessToken(TokenCoupleDto tokenCoupleDto);
}
