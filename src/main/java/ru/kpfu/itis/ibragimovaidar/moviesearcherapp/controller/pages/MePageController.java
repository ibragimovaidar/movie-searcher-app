package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller.pages;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.details.UserAccount;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/me")
@Controller
public class MePageController {

    private final UserService userService;

    @GetMapping
    public String getMe(@AuthenticationPrincipal UserAccount userAccount, Model model){
        UserResponse user = userService.getByUsername(userAccount.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("self", true);
        return "profile";
    }

    @PostMapping("/image")
    public String uploadImage(@AuthenticationPrincipal UserAccount userAccount, MultipartFile image){
        userService.updateImage(userAccount.getId(), image);
        return "redirect:/me";
    }
}
