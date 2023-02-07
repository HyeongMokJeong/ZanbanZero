package com.hanbat.zanbanzero.config;

import com.hanbat.zanbanzero.auth.jwt.JwtAuthFilter;
import com.hanbat.zanbanzero.auth.jwt.JwtLoginFilter;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.exception.controller.CustomAuthenticationEntryPoint;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;
    private final CorsFilter corsFilter;
    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtAuthFilter(authenticationConfiguration.getAuthenticationManager(), userRepository))
                .authorizeHttpRequests()
                .requestMatchers("/api/user/**").hasAnyRole("USER", "MANAGER")
                .requestMatchers("/api/manager/**").hasRole("MANAGER")
                .anyRequest().permitAll();

        return http.build();
    }
}
