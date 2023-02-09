package com.hanbat.zanbanzero.config;

import com.hanbat.zanbanzero.auth.jwt.JwtAuthFilter;
import com.hanbat.zanbanzero.auth.jwt.JwtLoginFilter;
import com.hanbat.zanbanzero.auth.jwt.JwtUtil;
import com.hanbat.zanbanzero.filter.ExceptionHandlerBeforeBasicAuthentication;
import com.hanbat.zanbanzero.filter.ExceptionHandlerBeforeUsernamePassword;
import com.hanbat.zanbanzero.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;
    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterBefore(new ExceptionHandlerBeforeUsernamePassword(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilterBefore(new ExceptionHandlerBeforeBasicAuthentication(), BasicAuthenticationFilter.class)
                .addFilter(new JwtAuthFilter(authenticationConfiguration.getAuthenticationManager(), userRepository))
                .authorizeHttpRequests()
                .requestMatchers("/api/user/**").hasAnyRole("USER", "MANAGER", "ADMIN")
                .requestMatchers("/api/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .anyRequest().permitAll();

        return http.build();
    }
}
