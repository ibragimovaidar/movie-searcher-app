package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizationHeaderException extends AuthenticationException {

    public AuthorizationHeaderException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthorizationHeaderException(String msg) {
        super(msg);
    }
}
