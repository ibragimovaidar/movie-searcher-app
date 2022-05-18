package ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.details.JwtTokenAuthenticationUserDetailsService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.details.SessionAuthenticationUserDetailsService;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.filter.JwtTokenAuthenticationFilter;
import ru.kpfu.itis.ibragimovaidar.moviesearcherapp.security.provider.JwtAccessTokenProvider;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenAuthenticationUserDetailsService jwtTokenAuthenticationUserDetailsService;

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    private static final String[] PERMIT_ALL = {
            "/api/v1/users/login",
            "/api/v1/users/register",
            "/api/v1/genres/**",
            "/register",
            "/css/**",
            "/js/**",
            "/img/**"
    };

    private static final String[] IGNORE = {
            "/account-swagger/api-docs",
            "/swagger-ui.html"
    };

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORE);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, PERMIT_ALL).permitAll()
                    .antMatchers(HttpMethod.POST, PERMIT_ALL).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login").permitAll()
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .defaultSuccessUrl("/me")
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                .and()
                    .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return new SessionAuthenticationUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAfter(jwtTokenAuthenticationFilter(), RequestHeaderAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
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
    public AuthenticationProvider authenticationProvider(){
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(jwtTokenAuthenticationUserDetailsService);
        authenticationProvider.setThrowExceptionWhenTokenRejected(false);
        return authenticationProvider;
    }

    @Override
    protected AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    public RequestHeaderAuthenticationFilter jwtTokenAuthenticationFilter(){
        return new JwtTokenAuthenticationFilter(jwtAccessTokenProvider, authenticationManager());
    }
*/
}
