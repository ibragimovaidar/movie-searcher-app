package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service;

import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.TokenCoupleDto;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.LoginRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.UserRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;

public interface UserService extends ImageEntityService {

    UserResponse getByUsername(String username);

    UserResponse register(UserRequest userRequest);

    TokenCoupleResponse login(LoginRequest loginRequest);
}
