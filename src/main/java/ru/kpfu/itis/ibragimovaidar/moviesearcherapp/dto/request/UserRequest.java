package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;
}
