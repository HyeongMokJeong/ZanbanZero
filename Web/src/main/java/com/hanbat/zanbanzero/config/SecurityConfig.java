package com.hanbat.zanbanzero.config;

import com.hanbat.zanbanzero.auth.jwt.JwtAuthFilter;
import com.hanbat.zanbanzero.auth.jwt.JwtLoginFilter;
import com.hanbat.zanbanzero.exception.filter.ExceptionHandlerBeforeBasicAuthentication;
import com.hanbat.zanbanzero.exception.filter.ExceptionHandlerBeforeUsernamePassword;
import com.hanbat.zanbanzero.exception.filter.SetFilterException;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterBefore(new ExceptionHandlerBeforeUsernamePassword(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilterBefore(new ExceptionHandlerBeforeBasicAuthentication(), BasicAuthenticationFilter.class)
                .addFilter(new JwtAuthFilter(authenticationConfiguration.getAuthenticationManager(), userRepository))
                .authorizeHttpRequests()
                .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }
}
