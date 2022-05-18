package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomePage {

    @GetMapping
    public String getHomePage(){
        return "redirect:/me";
    }
}
