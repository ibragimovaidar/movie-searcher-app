package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.LoginRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.UserRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.TokenCoupleResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;

@RequestMapping("/api/v1/users")
public interface UserApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    UserResponse register(@RequestBody UserRequest userRequest);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    TokenCoupleResponse login(@RequestBody LoginRequest loginRequest);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    UserResponse getByUsername(@PathVariable String username);

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/image")
    void updateImage(MultipartFile image);
}
