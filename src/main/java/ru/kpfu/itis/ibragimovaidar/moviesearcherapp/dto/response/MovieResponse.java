package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {

    private UUID id;

    private String name;

    private String description;

    private String country;

    private Integer averageRating;

    private LocalDate dateOfRelease;

    private String imageUrl;

    private String genreName;

    private List<PersonResponse> persons;

    private List<MovieCommentaryResponse> commentaries;
}
