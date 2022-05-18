package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieCommentaryRequest {

    private UUID movieId;

    private String text;
}
