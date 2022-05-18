package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.controller.pages;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.MovieCommentaryRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.MovieResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.details.UserAccount;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.service.MovieService;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/movies")
@Controller
public class MoviePageController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public String getById(@PathVariable UUID id, Model model){
        MovieResponse movie = movieService.getById(id);
        model.addAttribute("movie", movie);
        return "mPage";
    }

    @PostMapping("/comment")
    public String createComment(@AuthenticationPrincipal UserAccount userAccount, MovieCommentaryRequest movieCommentaryRequest){
        movieService.createComment(movieCommentaryRequest, userAccount.getId());
        return "redirect:/movies/" + movieCommentaryRequest.getMovieId();
    }
}
