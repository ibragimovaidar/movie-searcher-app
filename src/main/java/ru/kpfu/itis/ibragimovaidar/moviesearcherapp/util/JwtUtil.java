package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;

import static ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config.AppConst.BEARER;

@UtilityClass
public class JwtUtil {

    public static String parseTokenFromAuthorizationHeader(String header){
        return Optional.ofNullable(header)
                .filter(s -> s.startsWith(BEARER))
                .map(s -> s.substring(BEARER.length()))
                .orElse(null);
    }
}
