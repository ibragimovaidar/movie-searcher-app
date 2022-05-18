package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.details.JwtTokenAuthenticationUserDetailsService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.filter.JwtTokenAuthenticationFilter;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider.JwtAccessTokenProvider;

import java.util.Collections;

@Configuration
@Order(1)
@RequiredArgsConstructor
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenAuthenticationUserDetailsService jwtTokenAuthenticationUserDetailsService;

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    private static final String[] PERMIT_ALL = {
            "/api/v1/users/login",
            "/api/v1/users/register",
            "/api/v1/genres/**",
    };

    private static final String[] IGNORE = {
            "/account-swagger/api-docs",
            "/swagger-ui.html"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(jwtTokenAuthenticationFilter(), RequestHeaderAuthenticationFilter.class)
                .authenticationProvider(jwtAuthenticationProvider())
                .authorizeRequests()
                .antMatchers(PERMIT_ALL).permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }


    @Bean
    public AuthenticationProvider jwtAuthenticationProvider(){
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(jwtTokenAuthenticationUserDetailsService);
        authenticationProvider.setThrowExceptionWhenTokenRejected(false);
        return authenticationProvider;
    }

    @Bean
    protected AuthenticationManager jwtAuthenticationManager(){
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider()));
    }

    public RequestHeaderAuthenticationFilter jwtTokenAuthenticationFilter(){
        return new JwtTokenAuthenticationFilter(jwtAccessTokenProvider, jwtAuthenticationManager());
    }
}
