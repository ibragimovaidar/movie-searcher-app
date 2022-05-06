package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider.JwtAccessTokenProvider;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.HttpResponseUtil;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static ru.kpfu.itis.ibragimovaidar.moviesearcherapp.config.AppConst.AUTHORIZATION_HEADER;

@Slf4j
public class JwtTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    public JwtTokenAuthenticationFilter(JwtAccessTokenProvider jwtAccessTokenProvider, AuthenticationManager authenticationManager) {
        this.jwtAccessTokenProvider = jwtAccessTokenProvider;
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = JwtUtil.parseTokenFromAuthorizationHeader(((HttpServletRequest) request).getHeader(AUTHORIZATION_HEADER));
        if (Objects.nonNull(token)) {
            log.info("Loading user for authorization token: {}", token);
            UserResponse user = jwtAccessTokenProvider.userInfoByToken(token);
            PreAuthenticatedAuthenticationToken authenticationToken = new PreAuthenticatedAuthenticationToken(user, token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
