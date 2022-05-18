package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller.pages;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfilePageController {

    private final UserService userService;

    @GetMapping("/{username}")
    public String getProfile(@PathVariable String username, Model model){
        UserResponse user = userService.getByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
}
