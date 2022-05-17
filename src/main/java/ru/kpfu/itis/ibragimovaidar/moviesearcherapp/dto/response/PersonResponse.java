package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String description;

    private String imageUrl;
}
