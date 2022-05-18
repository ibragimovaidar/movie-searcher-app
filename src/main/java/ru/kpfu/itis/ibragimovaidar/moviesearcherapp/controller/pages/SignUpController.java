package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller.pages;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.UserRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class SignUpController {

    private final UserService userService;

    @GetMapping
    public String getRegisterPage(){
        return "signUp";
    }

    @PostMapping
    public String register(UserRequest userRequest){
        userService.register(userRequest);
        return "redirect:/login";
    }
}
