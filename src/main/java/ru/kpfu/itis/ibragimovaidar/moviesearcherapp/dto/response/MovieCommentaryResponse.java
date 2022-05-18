package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieCommentaryResponse {

    private Instant createDate;

    private String text;

    private UUID authorId;

    private String username;
}
