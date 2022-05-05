package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonRequest {

    private String firstName;

    private String lastName;

    private String middleName;

    private String description;
}
