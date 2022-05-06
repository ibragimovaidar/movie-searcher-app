package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.details;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.dto.response.UserResponse;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider.JwtAccessTokenProvider;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtTokenAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        UserResponse userResponse = (UserResponse) token.getPrincipal();
        String accessToken = String.valueOf(token.getCredentials());
        return Optional.ofNullable(userResponse)
                .map(user -> JwtUserDetails.builder()
                        .username(user.getUsername())
                        .authorities(user.getRoles().stream()
                                .map(role -> "ROLE_" + role.name())
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toSet()))
                        .expiration(jwtAccessTokenProvider.getExpiration(accessToken))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Unkwown user by token: " + token));
    }
}
