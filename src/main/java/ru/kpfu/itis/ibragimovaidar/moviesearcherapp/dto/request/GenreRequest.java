package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreRequest {

    @Size(max = 64)
    private String name;

    private String description;
}
