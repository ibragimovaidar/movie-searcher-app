package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.TokenCoupleDto;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.enums.Role;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.LoginRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.UserRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.NotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.ServiceException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.UnauthorizedException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception.UserNotFoundException;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.model.UserEntity;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.RoleRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.repository.UserRepository;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.UserService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.jwt.JwtTokenService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.mapper.UserMapper;

import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final JwtTokenService jwtTokenService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public UserResponse getByUsername(String username) {
        return null;
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        return userMapper.userToUserResponse(
                userRepository.save(
                        UserEntity.builder()
                                .username(userRequest.getUsername())
                                .email(userRequest.getEmail())
                                .hashPassword(passwordEncoder.encode(userRequest.getPassword()))
                                .roles(Collections.singleton(roleRepository.findByRole(Role.USER)
                                        .orElseThrow(() -> new NotFoundException("Role not found"))))
                                .build()
                )
        );
    }

    @Override
    public TokenCoupleResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException(loginRequest.getUsername() + " user not found"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getHashPassword())) {
            return jwtTokenService.generateToken(userMapper.userToUserResponse(user));
        }
        throw new UnauthorizedException("Failed to log in");
    }

    @Override
    public void updateImage(UUID id, MultipartFile image) {

    }
}
