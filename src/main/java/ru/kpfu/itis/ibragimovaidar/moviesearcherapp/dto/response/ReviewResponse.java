package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {

    private UUID id;

    private Integer rating;

    private String title;

    private String review;

    private MovieResponse movie;

    private Instant createDate;
}
