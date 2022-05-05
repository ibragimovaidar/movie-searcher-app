package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.UserRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;

@RequestMapping("/api/v1/users")
public interface UserApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    UserResponse register(@RequestBody UserRequest userRequest);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    UserResponse getByUsername(@PathVariable String username);
}
