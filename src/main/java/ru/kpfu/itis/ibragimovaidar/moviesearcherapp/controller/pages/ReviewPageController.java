package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller.pages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.ReviewRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.ReviewResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.details.UserAccount;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.ReviewService;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@Controller
public class ReviewPageController {

    private final ReviewService reviewService;

    @GetMapping("/{movieId}")
    public String getReviewForm(@PathVariable UUID movieId, Model model){
        model.addAttribute("movieId", movieId);
        return "reviewForm";
    }

    @PostMapping
    public String createReview(@AuthenticationPrincipal UserAccount userAccount, ReviewRequest reviewRequest){
        reviewService.createReview(reviewRequest, userAccount.getId());
        return "redirect:/me";
    }

    @GetMapping(value = "/ajax/id={id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReviewResponse getReview(@PathVariable UUID id){
        return reviewService.getById(id);
    }
}
