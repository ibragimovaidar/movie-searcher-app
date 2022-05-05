package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.api;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request.ReviewRequest;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.ReviewResponse;

import java.util.UUID;

@RequestMapping("/api/v1/reviews")
public interface ReviewApi {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    ReviewResponse create(ReviewRequest reviewRequest);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    ReviewResponse getById(@PathVariable UUID id);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/byUser/{userId}")
    Page<ReviewResponse> getAllByUserId(@PathVariable UUID userId);
}
